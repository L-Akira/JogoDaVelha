package Client.SocketLib;

import java.util.function.Function;

public class Listener {
	public final String event;
	public final Function<String, Void> callback;
	
	public Listener(String event, Function<String, Void> callback) {
		this.event = event;
		this.callback = callback;
	}
}
