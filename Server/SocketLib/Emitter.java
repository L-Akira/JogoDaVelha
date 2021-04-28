package Server.SocketLib;

import java.io.ObjectOutputStream;

public class Emitter {
	private ObjectOutputStream out;
		
	protected Emitter(ObjectOutputStream out) {
		this.out = out;
	}
	
	public void emit(String eventLabel, String msg) {
		try {
			String[] payload = new String[] {eventLabel, msg};
			this.out.writeObject(payload);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
