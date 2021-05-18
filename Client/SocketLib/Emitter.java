package Client.SocketLib;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Emitter {
	private Socket socket;
	private static Emitter instance;
	private ObjectOutputStream out;
	
	private Emitter(Socket socket) throws IOException {
		this.socket = socket;
		this.out = new ObjectOutputStream(this.socket.getOutputStream());
	}
	
	public static Emitter getInstance(Socket socket) throws IOException {
		if(instance == null)
			instance = new Emitter(socket);
		
		return instance;
	}
	
	public static Emitter getInstance() throws Exception {
		if(instance == null)
			throw new Exception("Must initialize with SocketSetup");
		
		return instance;
	}
	
	public void emit(String eventLabel, String msg) {
		try {
			String[] payload = new String[] {eventLabel, msg};
			this.out.writeObject(payload);
			this.out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
