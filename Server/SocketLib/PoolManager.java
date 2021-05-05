package Server.SocketLib;

import java.net.Socket;
import java.util.List;

public class PoolManager {
	private ClientPool clientPool;
	private List<Socket> clients;
	
	protected PoolManager() {
		this.clientPool = ClientPool.getInstance();
		this.clients = this.clientPool.getClients();
	}
	
	protected void kickDisconnectedClients() {
		for(int i = 0; i < this.clients.size(); i++) {
			Socket client = clients.get(i);
			if(client.isClosed())
				this.clientPool.removeClient(i);
		}
	}
}
