package Server.SocketLib;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class SocketRunner implements Runnable{
	private ObjectInputStream in;
	private Socket socket;
	private SocketHandler socketHandler;
	
	public SocketRunner(Socket socket, SocketHandler socketHandler) throws Exception {
		this.socketHandler = socketHandler;
		this.socket = socket;
		this.in = new ObjectInputStream(this.socket.getInputStream());
	}

	
	@Override
	public void run() {
		List<Listener> listeners = this.socketHandler.listeners;
		PoolManager poolManager = new PoolManager(listeners);
		
		try {
			while(true) {
				poolManager.kickDisconnectedClients();
				
				String[] data = (String[]) in.readObject();
				
				for (int i = 0; i < listeners.size(); i++) {
					Listener listener = listeners.get(i);
					
					if(listener.event.equals(data[0])) {
						listener.callback.apply(data[1]);
						break;
					}	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
