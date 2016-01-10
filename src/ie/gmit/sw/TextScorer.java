package ie.gmit.sw;

import java.util.Map;

public class TextScorer {
	private Map<String, Double> map = null;
	
	public TextScorer(Map<String, Double> m){
		this.map = m;
	}// End of TextScorer
	
	public double getScore(String text){
		double score = 0f;

		for (int i = 0; i < text.length(); i++){
			if (i + QuadGramMap.GRAM_SIZE <= text.length() -1){
				score += computeLogScore(text.substring(i, i + QuadGramMap.GRAM_SIZE));
			}// End of if
		}// End of for
		return score;
	}// End of getScore
	
	public double computeLogScore(String quadgram){
		if (map.containsKey(quadgram)){
			double frequency = map.get(quadgram);
			double total = (double) map.size();
			double probability = (double) (frequency/total);
			
			return Math.log10(probability);
		}// End of if
		else{
			return 0f;
		}// End of else
	}// End of computeLogScore
}// End of TextScorer