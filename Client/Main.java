package Client;

import Client.SocketLib.Connection;

public class Main {

	public static void main(String[] args) {
		Connection connection = Connection.getInstance(8080);
		try {
			connection.createConnection();
		} catch (Exception e) {
			System.out.println(e);;
		}
	}

}
