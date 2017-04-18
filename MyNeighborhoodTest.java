import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.Test;

public class MyNeighborhoodTest {

	Network n;
	HashSet<String> items;
	User test; //probably shouldn't need this
	String item = "cookies";
//	PriorityQueue<UserSimilarityTuple> q;
	
	
	@Before
	public void setUp() throws Exception {
//		q = new PriorityQueue<UserSimilarityTuple>();
		items = new HashSet<String>();
		items.add("cookies");
		items.add("ice cream");
		items.add("cake");
		n = new Network(items, 3);
		User d = new User("Danny");
		User r = new User("Ryan");
		User v = new User("Vicky");
		HashMap<String, Double> inner = new HashMap<String, Double>();
		inner.put("cookies", 5.0);
		inner.put("ice cream", 3.0);
		HashMap<String, Double> inner2 = new HashMap<String, Double>();
		inner2.put("ice cream", 1.0);
		inner2.put("cake", 4.0);
		HashMap<String, Double> inner3 = new HashMap<String, Double>();
		inner3.put("cookies", 2.0);
		inner3.put("cake", 4.0);
		HashMap<String, Double> innerTest = new HashMap<String, Double>();
		
		test = new User("test");
		innerTest.put("ice cream", 3.0);
		innerTest.put("cake", 3.0);
		
		n.networkMap.put(d, inner);
		n.networkMap.put(r, inner2);
		n.networkMap.put(v, inner3);
		n.networkMap.put(test, innerTest);
		
		
	}

	
	/**
	 * looking for who has cookies.
	 */
	@Test
	public void gatherNeighborsTest() {
		MyNeighborhood myN = new MyNeighborhood(n, test, "pearson", 3, this.item);
		PriorityQueue<UserSimilarityTuple> q = myN.gatherNeighbors(this.item);
		System.out.println("the size of the q is: " + q.size());
		for (UserSimilarityTuple tuple : q) {
			assertNotNull(tuple);
		}
		for (UserSimilarityTuple tuple : q) {
			System.out.println(tuple.n.id + " and the similarity score is: " + tuple.similarity);
		}
		
		
	}
	


}
