import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
//import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This class reads in a file and fills in a network object
 * An implementation of the file reader interface. 
 * @author DannyR
 *
 */
public class OldMoviesFileReader implements MyFileReader {

	public String file;
	private Network n;
	public HashSet<String> items = new HashSet<String>(15000);
	int k;
	public String item;
	public HashMap<String, User> usersMap = new HashMap<String, User>();
	public String type;
//	public HashSet<String> seen = new HashSet<String>(100);
	//should put this into the network object? Does this decouple?
	
	
	public OldMoviesFileReader(String filename, int k, String type) {
		file = filename;
		readFile(file);
		this.k = k;
		this.type = type;
	
	}

	@Override
	public void readFile(String file) {
		try {
			System.out.println("begun processing file");
			File myFile = new File(file);
			Scanner in = new Scanner(myFile);
			n = new Network(items, k, type);	///		 
			String person = "hello";
			User u = null;
			HashMap<String, Double> itemRating; //play with this
			while (in.hasNext()) {
//				HashMap<String, Double> itemRating; //play with this
				String line = in.nextLine();
				String[] values = line.split("::");
				String p = values[0];
				if (!items.contains(values[1])) {
					items.add(values[1]);
				}
				
				//if it's a new person
				if (!p.equals(person)) {
					itemRating = new HashMap<String, Double>();
					u = new User(values[0]);
					person = p;
					itemRating.put(values[1], Double.parseDouble(values[2]));
					n.networkMap.put(u, itemRating);
					usersMap.put(values[0], u);
					u.ratings += Double.parseDouble(values[2]);
					continue;
				}
				//if we've already seen this person
				else {
					u = usersMap.get(values[0]); 
					n.networkMap.get(u).put(values[1], Double.parseDouble(values[2]));
					u.ratings += Double.parseDouble(values[2]);
				}
				
			}

			in.close();
//			n.displayNetwork(); //for testing
		}
		catch (FileNotFoundException f) {
			System.out.println("couldn't find the file");
			f.printStackTrace();
		}
		
		
	}

	/**
	 * Getter method for the network created after readfile
	 * @return
	 */
	@Override
	public Network getNetwork() {
		return n;
	}
	
	
}
