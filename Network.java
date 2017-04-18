import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * This will be an adjacency list that will hold users, items, and ratings
 * 
 * @author DannyR
 *
 */
public class Network {
	public HashMap<User, HashMap<String, Double>> networkMap = new HashMap<User, HashMap<String, Double>>(300000);
	HashSet<String> itemlist;
	int k;
	String item;
	String type; //analysis type

	/**
	 * The constructor for the network class
	 * @param itemlist a list of all the items found in the file
	 * @param k the size of the neighborhood threshold
	 */
	public Network(HashSet<String> itemlist, int k, String type) {
		this.itemlist = itemlist;
		this.k = k;
		this.type = type;
	}

	/**
	 * The method used to gather the N Highest predictions 
	 * @param u the given User
	 * @param k the size of the neighborhood
	 * @return an arraylist of Item/Prediction tuples of size n
	 */
	public ArrayList<ItemPredictionTuple> nHighestPredictions(User u, int k, int n, String type) {
		ArrayList<ItemPredictionTuple> iptList;
		PriorityQueue<ItemPredictionTuple> pq = new PriorityQueue<ItemPredictionTuple>(300000);

		for (String item : itemlist) { 
			//if the neighbor does not have a rating for the item, then don't include it in the neighborhood calculation
			if (!networkMap.get(u).containsKey(item)) {
				//have to create a new neighborhood for each item
				//are we getting different users this way?
				Neighborhood neigh = new MyNeighborhood(this, u, type, k, item); 
				ArrayList<UserSimilarityTuple> a = neigh.getNeighborhood(); 
				PredictorFactory p = new PredictorFactory();
				Predictor pred = p.createPredictor("cf");
				double predictionScore = pred.generatePrediction(this, a, u, item);
				ItemPredictionTuple ItPredTup = new ItemPredictionTuple(item, predictionScore);
				pq.add(ItPredTup);
			}
		}

		iptList = getHighestPredictions(pq, n); 

		return iptList;
	}

	/**
	 * The method corresponding to the question - given a user u, and an item i, generate a prediction
	 * @param u the User
	 * @param item a string representing the item of interest
	 * @return a prediction
	 */
	public double singlePredictionGivenItem(User u, String item, String type){
		double prediction;
		Neighborhood neigh = new MyNeighborhood(this, u, type, k, item); 
		ArrayList<UserSimilarityTuple> a = neigh.getNeighborhood();
		System.out.println("the neighborhood for the user for this item is: "); 
		for (UserSimilarityTuple ust : a) {
			System.out.println(ust.n.id);
		}
		PredictorFactory p = new PredictorFactory();
		Predictor pred = p.createPredictor("cf");
		prediction = pred.generatePrediction(this, a, u, item);

		return prediction;
	}


	/**
	 * This method goes through the priority queue and adds the top n highest scores
	 * to an array list.
	 * @param p the priority queue of Item/prediction score tuples
	 * @param n the number of top predictions desired
	 * @return an array list of the n items for the top highest predictions for a given user
	 */
	public ArrayList<ItemPredictionTuple> getHighestPredictions(PriorityQueue<ItemPredictionTuple> p, int n){
		ArrayList<ItemPredictionTuple> ipt = new ArrayList<ItemPredictionTuple>(n);
		for (int i = 0; i < n; i++) {
			ipt.add(p.poll());
		}
		return ipt;
	}
	

	/**
	 * This method goes through the network hashmap and prints the contents
	 */
	public void displayNetwork(){
		for (User u : networkMap.keySet()) {
			for (String s : networkMap.get(u).keySet()) {
				System.out.println("The user is: " + u.id + " The item is: " + s + " The rating is: " + networkMap.get(u).get(s) );
				System.out.println();
			}
		}
	}

}
