import java.util.ArrayList;

/**
 * This class provides a cosine similarity implementation in CF Predictions
 * @author DannyR
 *
 */
public class CosineSimilarityCalculator implements SimilarityCalculator{

	@Override
	public UserSimilarityTuple calculateSimilarity(Network n, User user, User neighbor) {
		double similarityScore = 0;
		double numerator = 0;
		double denomenatorLeft = 0;
		double denomenatorRight = 0;
		double finalDenomenator = 0;
		
		ArrayList<ItemRatingsTriple> commonRatings = getCommonItems(n, user, neighbor);
		for (ItemRatingsTriple irt : commonRatings) {
			numerator += (irt.userRating * irt.userRating);
			denomenatorLeft += Math.pow(irt.userRating, 2);
			denomenatorRight += Math.pow(irt.userRating, 2);
		}
		
		finalDenomenator = Math.sqrt(denomenatorLeft) * Math.sqrt(denomenatorRight);
		similarityScore = numerator / finalDenomenator;
		
		UserSimilarityTuple ust = new UserSimilarityTuple(neighbor, similarityScore);
		return ust;
	}
	
	
	
	/**
	 * This method gets the common-rated items from each user, and returns the user's ratings for each
	 * @param network the hashmap of users, items, and ratings
	 * @param user 
	 * @param neighbor
	 * @return an Arraylist of ItemRatingsTriple objects that will be used in the Pearson Correlation
	 */
	public ArrayList<ItemRatingsTriple> getCommonItems(Network network, User user, User neighbor) {
		ArrayList<ItemRatingsTriple> commonItems = new ArrayList<ItemRatingsTriple>();
		for (String item : network.networkMap.get(user).keySet()) {
			if (network.networkMap.get(neighbor).keySet().contains(item)) {
				ItemRatingsTriple irt = new ItemRatingsTriple(item, network.networkMap.get(user).get(item), 
						network.networkMap.get(neighbor).get(item));
				commonItems.add(irt);
			}
		}
		return commonItems;
	}


}
