package Server.Socket;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketSetup {
	private static SocketSetup instance;
	private ServerSocket serverSocket;
	private int port;
	
	private SocketSetup(int portOptional) {
		this.port = portOptional;
	}
	
	private SocketSetup() {
		this.port = 4000;
	}
	
	public static SocketSetup getInstance(int socketPort) {
		if(instance == null)
			instance = new SocketSetup(socketPort);
		
		return instance;
	}
	
	public static SocketSetup getInstance() {
		if(instance == null)
			instance = new SocketSetup();
		
		return instance;
	}
	
	public Socket setUpConnection() throws Exception {
		this.serverSocket = new ServerSocket(this.port);
		System.out.println("Server created");
		return serverSocket.accept();	
	}
	
	public void closeConnection() throws Exception{
		if(serverSocket == null)
			throw new Exception("setUpConnection() must be executed beforehand");
		
		serverSocket.close();
	}
}
