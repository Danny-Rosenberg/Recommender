import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * The main driver and UI for the recommender system
 * @author DannyR
 *
 */
public class Main {
	//still need - given an item and a user, make a prediction
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Hello, welcome to the recommending system");
		Scanner in = new Scanner(System.in);
		System.out.println("Which file would you like to read?");
		System.out.println("Your choices are:");
		System.out.println("old movie ratings");
		System.out.println("book crossing");
		System.out.println("new movie ratings");
		String fileType = in.nextLine();
		FileReaderFactory frf = new FileReaderFactory();
		System.out.println("what kind of analysis would you like to perform?");
		System.out.println("pearson similarity");
		System.out.println("cosine similarity");
		System.out.println("baseline predictor");
		String analysisType = in.nextLine();
		MyFileReader f = frf.createFileReader(fileType, 20, analysisType);
//		FileReader f = new MyFileReader("ratings.dat", 20);
		Network network = f.getNetwork();
		
		
		System.out.println("enter the user number: ");
		String user = in.next();
		System.out.println("Alright, now enter an item:");
		String item = in.next();
		System.out.println("OK, now please enter the number of recommendations you would like:");
		int n = in.nextInt();
		in.close();		

		System.out.println("Great. Hold on a moment. ");

		//getting the n highest predictions
		//consider moving this into a facade class
		User chosenUser; 
		ArrayList<ItemPredictionTuple> ITP;
		for (User u : network.networkMap.keySet()){
			if (u.id.equals(user)) {
				chosenUser = u;
				ITP = network.nHighestPredictions(chosenUser, 20, n, analysisType);
				printPredictions(ITP);
				break;
			}
		}

		//Getting a single prediction given an item
		User cUser;
		for (User u : network.networkMap.keySet()){
			if (u.id.equals(user)) {
				cUser = u;  
				double prediction = network.singlePredictionGivenItem(cUser, item, analysisType);
				System.out.println("and for the given item: " + item + " the predicted score is: " + prediction);
				break;
			}

		}	



	}

	/**
	 * A printing method to show the highest n Predictions for a given user
	 * @param ITP an arraylist of ItempPrediction Tuples
	 */
	public static void printPredictions(ArrayList<ItemPredictionTuple> ITP) {
		for (ItemPredictionTuple ipt : ITP) {
			System.out.println("The item is: " + ipt.item + " and the predicted rating is: " + ipt.prediction);
		}
	}

}
