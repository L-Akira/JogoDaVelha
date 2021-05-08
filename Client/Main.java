package Client;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Client.SocketLib.Emitter;
import Client.SocketLib.SocketHandler;
import Client.SocketLib.SocketRunner;
import Client.SocketLib.SocketSetup;

public class Main {

	
	public static void main(String[] args) {
				
		try {
			SocketSetup socketSetup = new SocketSetup("localhost", 8080);
			SocketHandler socketHandler = new SocketHandler();
			
			Socket server = socketSetup.setupConnection();
			Emitter emitter = Emitter.getInstance();

			socketHandler.on("Ok", msg -> {
				System.out.println(msg);	
				emitter.emit("user", "Hi, User!!!!!!");
				return null;
			});
			socketHandler.on("Yes", msg -> {
				System.out.println(msg);	
				return null;
			});
			
			
			SocketRunner socketRunner = new SocketRunner(server, socketHandler);
			new Thread(socketRunner).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
