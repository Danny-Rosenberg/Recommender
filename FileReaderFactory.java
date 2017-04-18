/**
 * A factory for file reader objects
 * @author DannyR
 *
 */
public class FileReaderFactory {
	
	public MyFileReader createFileReader(String fileType, int k, String analysisType ) {
		MyFileReader f;
		if (fileType.equals("book crossing")) {
			f = new BookCrossingFileReader("BX-Book-Ratings.csv", k, analysisType);
			return f;
		} 
		else if (fileType.equals("small book crossing")) {
			f = new BookCrossingFileReader("book_crossing_small.csv", k, analysisType);
			return f;
			
		} else if (fileType.equals("old movie ratings")) {
			f = new OldMoviesFileReader("ratings.dat", k, analysisType);
			return f;	
		} else if (fileType.equals("new movie ratings")) {
			f = new NewMoviesFileReader("new_movie_ratings.csv", k, analysisType);
			return f;
		}
		return null;
		
	}
	
}
