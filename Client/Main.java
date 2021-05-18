package Client;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Client.SocketLib.Emitter;
import Client.SocketLib.MailBox;
import Client.SocketLib.SocketHandler;
import Client.SocketLib.SocketRunner;
import Client.SocketLib.SocketSetup;

public class Main {
	private static ExecutorService pool = Executors.newFixedThreadPool(4);
	
	public static void main(String[] args) {
				
		try {
			SocketSetup socketSetup = new SocketSetup("localhost", 8080);
			SocketHandler socketHandler = new SocketHandler();
			
			Socket server = socketSetup.setupConnection();

			socketHandler.on("Ok", (msg, emitter) -> {
				System.out.println(msg);	
				emitter.emit("user", "Hi, User!!!!!!");
				return null;
			});
			socketHandler.on("Yes", (msg, emitter) -> {
				System.out.println(msg);	
				return null;
			});

			MailBox mail = new MailBox(server);
			
			SocketRunner socketRunner = new SocketRunner(server, socketHandler, mail);
			pool.execute(mail);
			System.out.println("qwwww");
			pool.execute(socketRunner);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
