package Client.SocketLib;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Listener {
	public final String event;
	public final BiFunction<String, Emitter,  Void> callback;
	
	public Listener(String event, BiFunction<String, Emitter, Void> callback) {
		this.event = event;
		this.callback = callback;
	}
}
