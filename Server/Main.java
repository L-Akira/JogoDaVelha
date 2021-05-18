package Server;

import java.net.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Server.SocketLib.Emitter;
import Server.SocketLib.SocketHandler;
import Server.SocketLib.SocketRunner;
import Server.SocketLib.SocketSetup;

public class Main {
	private static ExecutorService pool = Executors.newFixedThreadPool(4);
	
	
	public static void main(String[] args) {	
		try {
			SocketSetup socketSetup = new SocketSetup(8080);
			SocketHandler socketHandler = new SocketHandler();
			Emitter emitter = socketHandler.getEmitter();
			 
			socketHandler.on("Connected", msg -> {
				emitter.emit("Ok", "ok");
				System.out.println(msg);				
				return null;
			});
			
			socketHandler.on("user", user -> {
				emitter.emit("Yes", "hello");
				System.out.println(user);
				return null;
			});
			
			
			while(true) {
				Socket client = socketSetup.setUpConnection(); 
				SocketRunner socketRunner = new SocketRunner(client, socketHandler);
				
				pool.execute(socketRunner);
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}	
	}

}
