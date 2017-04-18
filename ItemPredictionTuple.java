/**
 * This class is a custom data type used to hold an item, and the prediction score associated with it
 * @author DannyR
 *
 */
public class ItemPredictionTuple implements Comparable<ItemPredictionTuple> {

	public double prediction = 0;
	public String item;
	
	/**
	 * The constructor for the class
	 * @param item 
	 * @param prediction the prediction from the implemented Predictor class
	 */
	public ItemPredictionTuple(String item, double prediction) {
		this.item = item;
		this.prediction = prediction;
	}

	
	/**
	 * The comparator used for priority queues. Comparing the prediction scores.
	 */
	@Override
	public int compareTo(ItemPredictionTuple o) {
		return (int) (o.prediction - this.prediction);
	}
	
}
