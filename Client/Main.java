package Client;

import java.net.Socket;
import java.util.Scanner;

import Client.SocketLib.Emitter;
import Client.SocketLib.SocketHandler;
import Client.SocketLib.SocketRunner;
import Client.SocketLib.SocketSetup;
import Client.Utils.IDGenerator;
import Client.Utils.Printer;

public class Main {	
	public static void main(String[] args) {
		try {
			String id = IDGenerator.getInstance().generate();
			SocketSetup socketSetup = new SocketSetup("localhost", 8080);
			SocketHandler socketHandler = new SocketHandler();
			Socket server = socketSetup.setupConnection(id);
			Emitter emitter = Emitter.getInstance();
			
			
			
			//[wins, loses, ties]
			int[] result = new int[3];
			
			
			Printer printer = new Printer();
			Scanner scanner = new Scanner(System.in);
			
			socketHandler.on("Ok", msg -> {	
				if(id.equals(msg))
					emitter.emit("Join", id);
				return null;
			});
			
			socketHandler.on("Start", msg -> {
				
				printer.makePlay();
				String buffer = scanner.nextLine();
				System.out.println(buffer);
				emitter.emit("MakePlay", buffer+"-"+id);
				return null;
			});
			
			socketHandler.on("PlayerOut", msg -> {
				printer.aftermatch(result);
				
				try {
					socketSetup.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				scanner.close();
				return null;
			});
			
			socketHandler.on("VictoryPlayer", winner -> {
				if(winner.equals(id)) {
					result[0]++;
					System.out.println("Venceu");
				} else if(winner.equals("empate")) {
					result[2]++;
					System.out.println("Empate");
				} else {
					result[1]++;
					System.out.println("Perdeu");
				}
				
				String buffer = scanner.nextLine();
				
				if(buffer.equals("exit")) {
					printer.aftermatch(result);
					
					try {
						socketSetup.closeConnection();
					} catch (Exception e) {
						e.printStackTrace();
					}
					scanner.close();
					return null;
				}
					
				emitter.emit("MakePlay",  buffer+"-"+id);
				return null;
			});
			
			SocketRunner socketRunner = new SocketRunner(server, socketHandler);
			new Thread(socketRunner).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
