package Server;

import java.net.Socket;

import Server.Socket.SocketSetup;
import Server.SocketHandler.SocketHandler;
import Server.tictactoe.TicTacToe;

public class Main {

	public static void main(String[] args) {
		new Thread() {
			public void run() {
				try {
					
					SocketSetup socketSetup = SocketSetup.getInstance(8080);
					Socket socket = socketSetup.setUpConnection();
					System.out.println("Connection with client made");
					
					
					SocketHandler socketHandler = SocketHandler.getInstance(socket);
					socketHandler.on("Connected", msg -> {
						System.out.println(msg + "  irrrrrraaaaaaa");
						return null;
					});
					socketHandler.listen();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}.start();
	}

}
