package Server.SocketLib;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Emitter {
	private ClientPool pool;
		
	protected Emitter() {
		this.pool = ClientPool.getInstance();
	}
	
	public void emit(String eventLabel, String msg) {
		try {
			String[] payload = new String[] {eventLabel, msg};
			List<Socket> clients = pool.getClients();
			for(int i = 0; i < clients.size(); i++) {
				Socket client = clients.get(i);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				out.writeObject(payload);
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
