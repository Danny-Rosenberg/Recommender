import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class CFPredictorTest {

	Network n;
	HashSet<String> items;
	User test;
	ArrayList<UserSimilarityTuple> a;
	
	@Before
	public void setUp() throws Exception {
		
		
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
		
		a = new ArrayList<UserSimilarityTuple>();
		UserSimilarityTuple t = new UserSimilarityTuple(d, 3);
		UserSimilarityTuple tup = new UserSimilarityTuple(r, 5);
		UserSimilarityTuple tuple = new UserSimilarityTuple(v, 1);
		a.add(t);
//		a.add(tup);
		a.add(tuple);
		//size one passes, 
		
	}

	@Test
	public void generatePredictionTest() {
		CFPredictor cf = new CFPredictor();
		double result = cf.generatePrediction(n, a, test, "cookies");
		System.out.println(result);
		assertNotNull(result);
	}

}

