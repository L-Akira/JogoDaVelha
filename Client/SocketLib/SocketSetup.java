package Client.SocketLib;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketSetup {
	private Socket socket;
	private String url;
	private int port;

	public SocketSetup(String url, int port) {
		this.port = port;
		this.url = url;
	}
	
	public SocketSetup(String url) {
		this.port = 4000;
		this.url = url;
	}
	
	public Socket setupConnection() throws IOException { 
		this.socket = new Socket(this.url, this.port);
		System.out.println("Connected at: " + this.url + this.port);
		
	//	Emitter emitter = Emitter.getInstance(this.socket);
		//emitter.emit("Connected", "request from client");
		
		return this.socket;
	}
	
	protected Socket getSocket() {
		return this.socket;
	}
	
	public void closeConnection() throws Exception{
		if(this.socket == null)
			throw new Exception("setUpConnection() must be executed beforehand");
		
		this.socket.close();
	}
}
