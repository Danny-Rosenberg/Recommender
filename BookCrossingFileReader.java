import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * A file reader for reading the book crossing file
 * and creating/filling a network object
 * @author DannyR
 *
 */
public class BookCrossingFileReader implements MyFileReader {
	public String file;
	private Network n;
	public HashSet<String> items = new HashSet<String>(300000);
	int k;
	public String item;
	public HashMap<String, User> usersMap = new HashMap<String, User>();
	public String type;
	//	public HashSet<String> seen = new HashSet<String>(100);
	//should put this into the network object? Does this decouple?


	public BookCrossingFileReader(String filename, int k, String type) {
		file = filename;
		readFile(file);
		this.k = k;
		this.type = type;

	}


	/**
	 * Reads the file and makes/fills a network object
	 * @param the name of the file
	 */
	@Override
	public void readFile(String file) {
		try {
			System.out.println("begun processing file");
			File myFile = new File(file);
			
			BufferedReader bf = new BufferedReader(new FileReader(file));
			n = new Network(items, k, type);	///		 
			String person = "hello";
			User u = null;
			HashMap<String, Double> itemRating;
	//		int lineCount = 0;
			String line1;
//			try {
				bf.readLine();
				line1 = bf.readLine();
				while (line1 != null){
					
					String[] values = line1.split(";");
					if (values.length != 3) {
						System.out.println(line1 + "!!");
						line1 = bf.readLine();
						continue;
					}
	//				System.out.println(values[1]);
//					try {
						//getting rid of quotation marks
						String p = values[0].substring(1, values[0].length()-1); //person
						//finding the NoSuchElementException
						String i = values[1].substring(1, values[1].length()-1); //item
						Double r = Double.parseDouble(values[2].substring(1, values[2].length()-1)); //rating
	
						if (!items.contains(i)) {
							items.add(i);
						}
	
						//if it's a new person
						if (!p.equals(person)) {
							itemRating = new HashMap<String, Double>();
							u = new User(p);
							person = p;
							itemRating.put(i, r);
							n.networkMap.put(u, itemRating);
							usersMap.put(p, u);
							u.ratings += r;
							continue;
						}
						//if we've already seen this person
						else {
							u = usersMap.get(p); 
							n.networkMap.get(u).put(i, r);
							u.ratings += r;
						}
	
					
					
					line1 = bf.readLine();
				}		
					
				bf.close();	
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
			}
	}

	

	/**
	 * Getter method for the network object
	 */
	@Override
	public Network getNetwork() {
		return n;
	}

}
