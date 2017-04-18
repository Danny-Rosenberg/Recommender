import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class NetworkTest {
		
		Network n;
		HashSet<String> items;
		User test; //probably shouldn't need this
		String item = "cookies";
		User d;
		User v;
		
		@Before
		public void setUp() throws Exception {
			items = new HashSet<String>();
			items.add("cookies");
			items.add("ice cream");
			items.add("cake");
			n = new Network(items, 3);
			d = new User("Danny");
			User r = new User("Ryan");
			v = new User("Vicky");
			HashMap<String, Double> inner = new HashMap<String, Double>();
			inner.put("cookies", 5.0);
			inner.put("ice cream", 3.0);
			d.ratings = 8.0;
			HashMap<String, Double> inner2 = new HashMap<String, Double>();
			inner2.put("ice cream", 1.0);
			inner2.put("cake", 4.0);
			r.ratings = 5.0;
			HashMap<String, Double> inner3 = new HashMap<String, Double>();
			inner3.put("cookies", 2.0);
			inner3.put("cake", 4.0);
			v.ratings = 6.0;
			HashMap<String, Double> innerTest = new HashMap<String, Double>();
			
			test = new User("test");
			innerTest.put("ice cream", 3.0);
			innerTest.put("cake", 5.0);
			test.ratings = 8.0;
			 
			n.networkMap.put(d, inner);
			n.networkMap.put(r, inner2);
			n.networkMap.put(v, inner3);
			n.networkMap.put(test, innerTest); 
		} 
	
	
	@Test
	public void singlePredictionTest() {
		double prediction = n.singlePredictionGivenItem(test, "cookies");  
		System.out.println("the prediction here is: " + prediction); 
		System.out.println("Test average is: " + test.average);
		System.out.println("Danny's average is: " + d.average);
	}

}
