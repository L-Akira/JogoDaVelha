package Server.SocketLib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SocketHandler {
	protected List<Listener> listeners;
		
	public SocketHandler() throws IOException {
		this.listeners = new ArrayList<Listener>();
	}
		
	public void on(String event, Function<String, Void> callback) {		
		this.listeners.add(new Listener(event, callback));
	}
	
	public Emitter getEmitter() {
		return new Emitter();
	}
	
}
