/**
 * A factory for Predictor instances
 * @author DannyR
 *
 */
public class PredictorFactory {

	public Predictor createPredictor(String type) {
		Predictor p;
		if (type.equals("cf")) {
			p = new CFPredictor();
			return p;
		}
		return null;
	}
}
