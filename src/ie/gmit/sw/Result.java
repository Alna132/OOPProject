package ie.gmit.sw;

public class Result implements Resultable {
	
	// Variables
	private String plainText;
	private int key;
	private double score;
	
	// Constructors
	public Result(String plainText, int key, double score) {
		super();
		this.plainText = plainText;
		this.key = key;
		this.score = score;
	}// End of Result constructor
	
	// Getters & Setters
	public String getPlainText() {
		return plainText;
	}// End of getPlainText
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}// End of setPlainText
	
	public int getKey() {
		return key;
	}// End of getKey
	public void setKey(int key) {
		this.key = key;
	}// End of setKey
	
	public double getScore() {
		return score;
	}// End of getScore
	public void setScore(double score) {
		this.score = score;
	}// End of setScore
	
	@Override
	public int compareTo(Resultable arg0) {
		return 0;
	}// End of compareTo
}// End of Result
