import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * An implementation of the Neighborhood interface.
 * Uses the similarity calculator t
 * @author DannyR
 *
 */
public class MyNeighborhood implements Neighborhood {

	public Network network;
	public User user;
	public SimilarityCalculator s;
	public int k;
	public String item;


	/**
	 * The constructor for the MyNeighborhood class
	 * @param network the hashmap of users, items, and ratings
	 * @param user the given user through main
	 * @param similarityType the type of similarity calculator 
	 * @param k the size of the neighborhood threshold
	 * @param item the item of interest, provided through main
	 */
	public MyNeighborhood(Network network, User user, String similarityType, int k, String item) {
		this.network = network;
		SimilarityCalculatorFactory f = new SimilarityCalculatorFactory();
		s = f.createSimilarityCalculator(similarityType); 
		this.user = user;
		this.k = k;
		this.item = item;
		//		System.out.println("the item in this neighborhood is " + this.item);
	}

	/**
	 * Adds Tuples of type similarity - user to the priority queue, whose ordering is based on similarity
	 * @param item a string that represents the given item
	 * @return A priority queue of user similarity tuples
	 */
	@Override
	public PriorityQueue<UserSimilarityTuple> gatherNeighbors(String item) {
		//Here we only want to consider the neighbor if they have a rating for the item
		PriorityQueue<UserSimilarityTuple> q = new PriorityQueue<UserSimilarityTuple>();


		for (User u : network.networkMap.keySet()) {
			if (u.equals(user)) {
				continue;
			}
			if (network.networkMap.get(u).containsKey(item)) { 
				q.add(s.calculateSimilarity(network, user, u)); 
			}
		}

		return q;
	}


	/**
	 * Slims down the priority queue and takes the top k most similar users that have rated the given item
	 * @param a priority queue of User similarity tuples
	 * @k the neighborhood threshold
	 * @return An array list of the k most similar UserSimilarityTuples to the given user
	 */
	@Override
	public ArrayList<UserSimilarityTuple> generateNeighborhood(PriorityQueue<UserSimilarityTuple> q, int k) {
		ArrayList<UserSimilarityTuple> ust = new ArrayList<UserSimilarityTuple>();
		//		System.out.println("the size of the q inside generate neighbor hood is : " + q.size());
		int limiter = Math.min(q.size(), k); //taking the minimum
		for (int i = 0; i < limiter; i++) {
			//			System.out.println(q.peek().n.id);
			ust.add(q.poll());
		}
		return ust;
	}

	/**
	 * This method calls the gatherNeighbors and generateNeighborhood functions
	 * @return an arraylist of userSimilarityTuple objects
	 */
	public ArrayList<UserSimilarityTuple> getNeighborhood() {
		PriorityQueue<UserSimilarityTuple> q = gatherNeighbors(this.item);
		//		System.out.println("within get neighborhood, the size of q is: " + q.size());
		ArrayList<UserSimilarityTuple> a = generateNeighborhood(q, k);
		return a;
	}


	/**
	 * Performs the collaborative filtering-method of prediction
	 * Note that we don't require the item here - the composition of the neighborhood is based on the item
	 * @param ust a neighborhood of similar users based on an item
	 * @param u the given user we want a prediction for
	 * @return a prediction
	 */
	@Override
	public double generatePrediction(Network network, ArrayList<UserSimilarityTuple> ust, User u, String type, String item) {
		PredictorFactory pf = new PredictorFactory();
		Predictor p = pf.createPredictor(type);
		return p.generatePrediction(network, ust, u, item);

	}





}
