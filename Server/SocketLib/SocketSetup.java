package Server.SocketLib;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketSetup {
	private ServerSocket serverSocket;
	private int port;
	
	public SocketSetup(int portOptional) throws IOException {
		this.port = portOptional;
		this.serverSocket = new ServerSocket(this.port);
		System.out.println("Server created at: " + this.port);
	}
	
	public SocketSetup() throws IOException {
		this.port = 4000;
		this.serverSocket = new ServerSocket(this.port);
		System.out.println("Server created at: " + this.port);
	}
	
	public Socket setUpConnection() throws Exception {
		Socket socket = serverSocket.accept();
		ClientPool.getInstance().setClient(socket);
		return socket;
	}
	
	public void closeConnection() throws Exception{
		if(serverSocket == null)
			throw new Exception("setUpConnection() must be executed beforehand");
		
		serverSocket.close();
	}
}
