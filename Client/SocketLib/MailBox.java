package Client.SocketLib;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class MailBox implements Runnable  {
	public LinkedBlockingQueue<String[]> mailbox;
	//private ObjectInputStream in;
	private Socket socket;
	public MailBox(Socket socket){
		this.mailbox = new LinkedBlockingQueue<String[]>();
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			
			while(true) {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				String[] message = (String[]) in.readObject();
				mailbox.put(message);	
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
