/**
 * This class stores items, and the ratings for a given user and its neighbor, 
 * for use in SimilarityCalculator
 * @author DannyR
 *
 * RatingA is the given user's rating
 * RatingB is the given neighbor's rating
 *
 */
public class ItemRatingsTriple {

	public String item;
	public double userRating;
	public double neighRating;
	
	/**
	 * The constructor for the object
	 * @param item the common item between the users
	 * @param userRating the rating for the user
	 * @param neighborRating the rating for the 
	 */
	public ItemRatingsTriple(String item, double userRating, double neighborRating) {
		this.item = item;
		this.userRating = userRating;
		this.neighRating = neighborRating;
	}
	
}
