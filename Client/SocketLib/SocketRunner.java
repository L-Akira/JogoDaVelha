package Client.SocketLib;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;




public class SocketRunner implements Runnable {
	//private ObjectInputStream in;
	private Socket socket;
	private SocketHandler socketHandler;
	private LinkedBlockingQueue<String[]> mailbox;
	private MailBox mail;
	private Emitter emitter;
	
	public SocketRunner(Socket socket, SocketHandler socketHandler, MailBox mail) throws Exception {
		this.socketHandler = socketHandler;
		this.socket = socket;
		//this.in = new ObjectInputStream(this.socket.getInputStream());
		this.mailbox = new LinkedBlockingQueue<String[]>();
		this.mail = mail;
		 this.emitter = Emitter.getInstance(socket);
	}

	@Override
	public void run() {
		/*
		Thread handleIncomingMsg = new Thread() {
			public void run() {
				try {
					while(true) {
						String[] message = (String[]) in.readObject();
						mailbox.put(message);					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		handleIncomingMsg.setDaemon(true);
		handleIncomingMsg.start();
		*/
		
		
		List<Listener> listeners = this.socketHandler.listeners;
		
		try {
			this.emitter.emit("Connected", "request from client");
		
			while (true) {
				this.mailbox = mail.mailbox;
				if(this.mailbox.size() == 0) {
					continue;
				}
				
				String[] data = this.mailbox.take();
				
				for(int i = 0; i < listeners.size(); i++) {
					Listener listener = listeners.get(i);
					
					if(listener.event.equals(data[0])) {
						listener.callback.apply(data[1], this.emitter);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
