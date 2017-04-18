/**
 * This custom object holds neighbor User objects, and a similarity score related to the given user
 * at the top of the program
 * @author DannyR
 *
 */
public class UserSimilarityTuple implements Comparable<UserSimilarityTuple> {

	public User n; //a neighbor
	public double similarity;

	/**
	 * The constructor for the tuple
	 * @param n the neighbor User object 
	 * @param similarity the score from calculateSimilarity
	 */
	public UserSimilarityTuple(User n, double similarity) {
		//		super();
		this.n = n;
		this.similarity = similarity;
	}

	@Override
	public int compareTo(UserSimilarityTuple t) {
		return (int) (t.similarity - this.similarity);

	}

}
