import java.util.ArrayList;

/**
 * This is the implementation of the Predictor interface. 
 * This uses collaborative filtering as a method of making predictions
 * @author DannyR
 *
 */
public class CFPredictor implements Predictor {

	public Neighborhood hood;
	public String item;
	public User user;

	/**
	 * Performs a collaborative filtering prediction
	 * @param n The network of users, items, and ratings
	 * @param ust the ArrayList of UserSimilarityTuples
	 * @param user the User object of interest
	 * @param item the item we're making a prediction for
	 * @return a double with the prediction for this  
	 */
	@Override
	public double generatePrediction(Network n, ArrayList<UserSimilarityTuple> ust, User user, String item) {
		double numerator = 0.0; 
		double denomenator = 0.0;
		for (UserSimilarityTuple tuple : ust) {
			numerator += tuple.similarity * (n.networkMap.get(tuple.n).get(item) - tuple.n.average);
			denomenator += Math.abs(tuple.similarity); 
		}
		if (denomenator == 0.0) { 
			return user.average;
		}
		return user.average + (numerator/denomenator);

	}


}
