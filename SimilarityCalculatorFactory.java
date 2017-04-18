/**
 * A class to decouple the creation of Similarity Calculator objects
 * @author DannyR
 *
 */
public class SimilarityCalculatorFactory {


	/**
	 * This is a simple factory that will return the proper type of similarity calculator object
	 * @param type
	 * @return
	 */
	public SimilarityCalculator createSimilarityCalculator(String type) {
		SimilarityCalculator s;
		if (type.equals("pearson")) {
			s = new PearsonSimilarityCalculator();
			return s;
		}
		else if (type.equals("cosine similarity")){
			s = new CosineSimilarityCalculator();
			return s;
		}
		else {
			return null;
		}
	}


}
