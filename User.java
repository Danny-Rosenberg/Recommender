/**
 * This class represents a single user in the recommender system
 * @author Danny
 *
 */
public class User  {

	public String id;
	public double similarity;
	public double ratings;
    public double average;
	
	
	public User(String id) {
		this.id = id;
	}

	
}
