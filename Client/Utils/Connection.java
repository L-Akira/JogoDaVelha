package Client.Utils;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
	private int port;
	private static Connection instance;
	private String[] handshake;
	private Socket socket;
	private ObjectOutputStream out;

	
	private Connection(int port) {
		this.port = port;
		this.handshake = new String[2];
		this.handshake[0] = "Connected";
		this.handshake[1] = "hello world";
	}
	
	public static Connection getInstance(int port) {
		if(instance == null)
			instance = new Connection(port);
		
		return instance;
	}
	
	public void createConnection() throws Exception {
		this.socket = new Socket("localhost", this.port);
		try {
			this.out = new ObjectOutputStream(this.socket.getOutputStream());
			this.out.writeObject(this.handshake);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void closeConnection() throws Exception {
		if(this.socket == null) 
			throw new Exception("createConnection() must be executed beforehand");
		
		this.socket.close();
	}
}
