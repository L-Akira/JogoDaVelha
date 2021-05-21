package Server.SocketLib;

import java.net.Socket;
import java.util.List;

public class PoolManager {
	private ClientPool clientPool;
	private List<Socket> clients;
	private List<Listener> listeners;
	
	protected PoolManager(List<Listener> listeners) {
		this.clientPool = ClientPool.getInstance();
		this.clients = this.clientPool.getClients();
		this.listeners = listeners;
	}
	
	protected void kickDisconnectedClients() {
		for(int i = 0; i < this.clients.size(); i++) {
			Socket client = clients.get(i);
			
			if(client.isClosed()) {
				this.clientPool.removeClient(i);
				
				for(Listener listener: listeners) {
					if(listener.event.equals("Disconnect")) {
						listener.callback.apply(String.valueOf(i));
						break;
					}
				}
				
			}
		}
	}
}
