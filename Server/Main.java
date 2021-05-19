package Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
			List arrayPlayer = new ArrayList<Integer>();

			socketHandler.on("Connected", msg -> {
				emitter.emit("Ok", "ok");
				System.out.println(msg);
				return null;
			});

			socketHandler.on("Ok", id -> {
				arrayPlayer.push(id);
			});

			socketHandler.on("MakePlay", play -> {
				emitter.emit("Thanks", "thanks");
				String[] payload = play.split("-");
				if (payload[1] = arrayPlayer.get(0))
					this.jokempo.setPlayer1(payload[0]);
				else
					this.jokempo.setPlayer2(payload[0]);

				if (jokempo.play() == 1 && jokempo.getPlayer1() != 0 && jokempo.getPlayer2() != 0) {
					emitter.emit("VictoryPlayer", "vitoria1");
				}

				else if (jokempo.play() == 2 && jokempo.getPlayer1() != 0 && jokempo.getPlayer2() != 0) {
					emitter.emit("VictoryPlayer", "vitoria2");
				}

				else if (jokempo.play() == 3 && jokempo.getPlayer1() != 0 && jokempo.getPlayer2() != 0) {
					emitter.emit("VictoryPlayer", "Empate");
				}

				jokempo.reset();
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
