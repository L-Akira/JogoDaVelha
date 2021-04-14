package Server;

import java.net.Socket;

import Server.Socket.SocketSetup;

public class Main {

	public static void main(String[] args) {
		new Thread() {
			public void run() {
				try {
					SocketSetup socketSetup = SocketSetup.getInstance(8080);
					Socket socket = socketSetup.setUpConnection();
					System.out.println("Connection with client made");
					socketSetup.closeConnection();
					System.out.println("success");
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}.start();
	}

}
