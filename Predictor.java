import java.util.ArrayList;

/**
 * This interface contains the methods required for 
 * a prediction algorithm in the recommender system
 * @author DannyR
 *
 */
public interface Predictor {

	public double generatePrediction(Network n, ArrayList<UserSimilarityTuple> ust, User user, String item);
	
}
