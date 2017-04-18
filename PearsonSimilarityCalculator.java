import java.util.ArrayList;
/**
 * This class is an implementation of the Similarity Calculator class
 * It performs a pearson calculation
 * @author DannyR
 *
 */
public class PearsonSimilarityCalculator implements SimilarityCalculator {

	/**
	 * This method does a pearson correlation between two users
	 * @param network the adjacency list of users, items, and ratings
	 * @param user the given user
	 * @param neighbor a neighbor of the user
	 * @return A Tuple of the neighbor and the similarity between it and the given user
	 */
	@Override
	public UserSimilarityTuple calculateSimilarity(Network network, User user, User neighbor) {
		//calculating the average rating for each user
		user.average = user.ratings/network.networkMap.get(user).keySet().size();
//		System.out.println("user rating is: " + user.ratings + " and user average is " + user.average);
		neighbor.average = neighbor.ratings/network.networkMap.get(neighbor).keySet().size();
//		System.out.println("neighbor ratings is: " + neighbor.ratings + " and the neighbor average is: " + neighbor.average);
		double numerator = 0; 
		double leftDenom = 0;
		double rightDenom = 0;
		double similarityScore = 0;
		ArrayList<ItemRatingsTriple> irt = getCommonItems(network, user, neighbor);
			for (ItemRatingsTriple i : irt) { 
				numerator += ((i.userRating - user.average) * (i.neighRating - neighbor.average));
				leftDenom += Math.pow(i.userRating - user.average, 2);
				rightDenom += Math.pow(i.neighRating - neighbor.average, 2);
			}
		
		if (leftDenom == 0 || rightDenom == 0) {
			UserSimilarityTuple t = new UserSimilarityTuple(neighbor, .5); //tie breaking
			return t;
		}
		
		similarityScore = numerator / (Math.sqrt(leftDenom) * Math.sqrt(rightDenom));
		UserSimilarityTuple t = new UserSimilarityTuple(neighbor, similarityScore);
// 		System.out.println("the new tuple's user id is: " + t.n.id + " and the score is: " + t.similarity);
		
		return t;
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
//		System.out.println("the number of commonItems is: " + commonItems.size());
		return commonItems;
	}

}
