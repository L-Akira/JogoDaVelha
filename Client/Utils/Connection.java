package Client.Utils;

import java.io.OutputStream;
import java.net.Socket;

public class Connection {
	private int port;
	private static Connection instance;
	private String handshakeMsg;
	private Socket socket;
	private OutputStream out;

	
	private Connection(int port) {
		this.port = port;
		this.handshakeMsg = "Connected";
	}
	
	public static Connection getInstance(int port) {
		if(instance == null)
			instance = new Connection(port);
		
		return instance;
	}
	
	public void createConnection() throws Exception {
		this.socket = new Socket("localhost", this.port);
		try {
			this.out = this.socket.getOutputStream();
			this.out.write(this.handshakeMsg.getBytes());
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
