package Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Server.SocketLib.ClientPool;
import Server.SocketLib.Emitter;
import Server.SocketLib.SocketHandler;
import Server.SocketLib.SocketRunner;
import Server.SocketLib.SocketSetup;
import Server.Jokempo.Jokempo;

public class Main {
	private static ExecutorService pool = Executors.newFixedThreadPool(4);

	public static void main(String[] args) {
		try {
			SocketSetup socketSetup = new SocketSetup(8080);
			SocketHandler socketHandler = new SocketHandler();
			Emitter emitter = socketHandler.getEmitter();
			Jokempo jokempo = new Jokempo();
			List<Integer> arrayPlayer = new ArrayList<Integer>();

			socketHandler.on("Connected", msg -> {
				System.out.println(msg);
				emitter.emit("Ok", msg);
				return null;
			});

			socketHandler.on("Join", id -> {
				arrayPlayer.add(Integer.parseInt(id));
				
				if(ClientPool.getInstance().connectedClientsAmount() == 2)
					emitter.emit("Start", id);
				return null;
			});
			
			socketHandler.on("MakePlay", play -> {
				String[] payload = play.split("-");
				int id = Integer.parseInt(payload[1]);
				int choice = Integer.parseInt(payload[0]);
				
				if (id == arrayPlayer.get(0))
					jokempo.setPlayer1(choice);
				else
					jokempo.setPlayer2(choice);
				
				String winner;
				
				if (jokempo.play() == 1 && arrayPlayer.size() == 2) {
					System.out.println("a");
					winner = String.valueOf(arrayPlayer.get(0));	
					emitter.emit("VictoryPlayer", winner);
				} else if (jokempo.play() == 2 && arrayPlayer.size() == 2) {
					System.out.println("b");
					winner = String.valueOf(arrayPlayer.get(1));
					emitter.emit("VictoryPlayer", winner);
				} else if (jokempo.play() == 3 && arrayPlayer.size() == 2) {
					System.out.println("c");
					winner = "empate";
					emitter.emit("VictoryPlayer", winner);
				} 
								
				
				//jokempo.reset();
				return null;
			});
			
			socketHandler.on("Disconnect", index -> {
				arrayPlayer.remove(Integer.valueOf(index));
				emitter.emit("PlayerOut", "");
				return null;
			});

			while (true) {
				Socket client = socketSetup.setUpConnection();
				SocketRunner socketRunner = new SocketRunner(client, socketHandler);

				pool.execute(socketRunner);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
