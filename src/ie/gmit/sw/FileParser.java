package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class FileParser {

	String line = null;
	
	public Map<String, Double> parse (String file) throws NumberFormatException, IOException 
	{
		Map<String, Double> temp = new ConcurrentHashMap<String, Double>();
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream("4grams.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}// End of try catch
		
		while ((line = input.readLine()) != null) {
			
			String[] Score = line.split(" ");
			temp.put(Score[0], Double.parseDouble(Score[1]));	
				
			 }// End of while
		input.close();
		return temp;
	}// End of Map
}// End of FileParser
