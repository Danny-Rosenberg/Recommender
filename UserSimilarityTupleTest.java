import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.Test;

public class UserSimilarityTupleTest {

	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		User d = new User("Danny");
		User r = new User("Ryan");
		User v = new User("Vicky");
		UserSimilarityTuple t = new UserSimilarityTuple(d, 3);
		UserSimilarityTuple tup = new UserSimilarityTuple(r, 5);
		UserSimilarityTuple tuple = new UserSimilarityTuple(v, 1);
		PriorityQueue<UserSimilarityTuple> p = new PriorityQueue<UserSimilarityTuple>();
		p.add(t);
		p.add(tup);
		p.add(tuple);
		ArrayList<UserSimilarityTuple> a = new ArrayList<UserSimilarityTuple>();
		a.add(p.poll());
		a.add(p.poll());
		a.add(p.poll());
		
	}

}
