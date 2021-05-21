package Client.Utils;

import java.util.Random;

public class IDGenerator {
	private static IDGenerator instance;
	private Random random;
	
	public IDGenerator() {
		this.random = new Random();
	}
	
	public static IDGenerator getInstance() {
		if(instance == null)
			instance = new IDGenerator();
	
		return instance;
	}
	
	public String generate() {
		int number = random.ints(0, 700)
		.findFirst()
		.getAsInt();
		
		return String.valueOf(number);
	}
	
}
