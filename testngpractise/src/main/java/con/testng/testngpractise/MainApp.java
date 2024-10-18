package con.testng.testngpractise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
	 * Main class for the application demonstrating simple operations.
	 */
	public class MainApp {
	    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

	    /**
	     * Main method that runs the application.
	     *
	     * @param args command line arguments
	     */
	    public static void main(String[] args) {
	        logger.info("Application started.");
	        
	        int result = add(5, 3);
	        logger.info("Addition result: {}", result);
	        
	        logger.info("Application finished.");
	    }

	    /**
	     * Adds two integers and returns the result.
	     *
	     * @param a first integer
	     * @param b second integer
	     * @return sum of a and b
	     */
	    public static int add(int a, int b) {
	        return a + b;
	    }
}