/**
 * This interface provides the required methods for
 * any similarity calculator algorithm for the recommender 
 * @author DannyR
 *
 */
public interface SimilarityCalculator {

	public UserSimilarityTuple calculateSimilarity(Network n, User user, User neighbor);
	
}
