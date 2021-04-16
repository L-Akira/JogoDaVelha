package Server.SocketHandler;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import Server.dtos.Listener;
//TODO: work at socketSetup() extension
public class SocketHandler {
	private List<Listener> listeners;
	private Socket socket;
	private boolean isListening;
	private static SocketHandler instance;
	
	private SocketHandler(Socket socket) {
		this.listeners = new ArrayList<Listener>(); 
		this.socket = socket;
		this.isListening = false;
	}
	
	public static SocketHandler getInstance(Socket socket) {
		if(instance == null)
			instance = new SocketHandler(socket);
		
		return instance;
	}
	
	public void on(String event, Function<String, Void> callback) throws Exception{
		if(this.isListening)
			throw new Exception("Cannot assign events while listening");
		
		this.listeners.add(new Listener(event, callback));
	}
	
	public void emit(String event, String message) {
		//TODO: create emit logic
	}
	
	public void emit(String event) {
		//TODO: create emit logic
	}
	
	public void listen() throws Exception {
		this.isListening = true;
		ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
		String[] data = (String[]) in.readObject();
		
		for (int i = 0; i < listeners.size(); i++) {
			Listener listener = listeners.get(i);
			
			if(listener.event.equals(data[0])) {
				listener.callback.apply(data[1]);
				break;
			}	
		}
	}
}
