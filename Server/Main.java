package Server;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Server.SocketLib.Emitter;
import Server.SocketLib.SocketHandler;
import Server.SocketLib.SocketRunner;
import Server.SocketLib.SocketSetup;
import Server.tictactoe.TicTacToe;

public class Main {
	private static ExecutorService pool = Executors.newFixedThreadPool(4);
	
	
	public static void main(String[] args) {	
		try {
			SocketSetup socketSetup = new SocketSetup(8080);
			SocketHandler socketHandler = new SocketHandler();
			
			socketHandler.on("Connected", (msg, emmiter) -> {
				emmiter.emit("Ok", "RECEIVED");
				System.out.println(msg);				
				return null;
			});
			
			socketHandler.on("user", (user, emmiter) -> {
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
