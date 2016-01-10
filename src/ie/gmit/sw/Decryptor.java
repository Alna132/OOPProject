package ie.gmit.sw;

import java.util.concurrent.*;

public class Decryptor implements Runnable {

	// Variables
	private BlockingQueue<Resultable> queue;
	private int key;
	private String cypherText;
		
	// Constructor
	public Decryptor(BlockingQueue<Resultable> queue, String cypherText, int key) {
		super();
		this.queue = queue;
		this.cypherText = cypherText;
		this.key = key;
	}// End of Decryptor constructor
	
	public void run() {
		RailFence railfence1 = new RailFence();
		String plainText = railfence1.decrypt(cypherText, key);
		Resultable result = null;
		result.setKey(key);
		result.setPlainText(plainText);
		
		try {
			queue.put(result);
		}// End of try
		catch (InterruptedException e) {
			e.printStackTrace();
		}// End of catch
	}// End of run
}// End of Decryptor
