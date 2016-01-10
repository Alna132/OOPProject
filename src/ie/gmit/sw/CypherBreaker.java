package ie.gmit.sw;

import java.util.concurrent.*;

public class CypherBreaker {

	// Variables
	private static final int MAX_QUEUE_SIZE = 100;
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	
	// Constructor
	public CypherBreaker(String cypherText)
	{
		queue = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);
		this.cypherText = cypherText;
		init();
	}// End of CypherBreaker constructor
	
	public void init()
	{
		//start a load of producers
		//put into separate classes
		for(int i = 2; i <cypherText.length()/2; i++)
		{
			new Thread(new Decryptor(queue, cypherText, i)).start();
		}// End of for
		new Thread(new Runnable()
		{
			public void run() {
				while(!queue.isEmpty())
				{	
					try {
						Resultable r = queue.take();
						/*
						 * create poisonResult class that implements Resultable 
						 * white diamond straight line uml connection to result
						 * 
						 * MUTEX LOCK a volatile counter and increment until counter is the size of the 
						 * blocking queue was at the start
						 * when the numbers are the same, put a poisonResult into queue to kill it off
						 */
						if(r instanceof /*Poison*/Result)
						{
							return;
						}// End of if
						//do something......
					}// End of try
					catch (InterruptedException e) {
						e.printStackTrace();
					}// End of catch
				}// End of while
			}// End of run
		});// End of Thread
	}// End of init
}// End of CypherBreaker
