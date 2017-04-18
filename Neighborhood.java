import java.util.ArrayList;
import java.util.PriorityQueue;
/**
 * This interface dictates the behavior of concrete Neighborhood types
 * @author DannyR
 *
 */
public interface Neighborhood {

	public PriorityQueue<UserSimilarityTuple> gatherNeighbors(String item);
	
	public ArrayList<UserSimilarityTuple> generateNeighborhood(PriorityQueue<UserSimilarityTuple> q, int k);
	
	public double generatePrediction(Network network, ArrayList<UserSimilarityTuple> ust, User u, String type, String item);
	
	public ArrayList<UserSimilarityTuple> getNeighborhood();
	
}
