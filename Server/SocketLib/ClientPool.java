package Server.SocketLib;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientPool {
	private List<Socket> pool;
	private static ClientPool instance;
	
	private ClientPool() {
		this.pool = new ArrayList<Socket>();
	}
	
	protected static ClientPool getInstance() {
		if(instance == null)
			instance = new ClientPool();
		
		return instance;
	}
	
	protected void setClient(Socket client) {
		this.pool.add(client);
	}
	
	public Socket getClient(int index) {
		return this.pool.get(index);
	}
	
	protected List<Socket> getClients() {
		return this.pool;
	}
	
	protected void removeClient(int index) {
		this.pool.remove(index);
	}
}
