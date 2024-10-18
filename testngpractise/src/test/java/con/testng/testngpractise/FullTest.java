package con.testng.testngpractise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
/**
 * A test class that demonstrates the use of various TestNG annotations
 * along with SLF4J for logging.
 */

public class FullTest {

	      
	    private static final Logger logger = LoggerFactory.getLogger(FullTest.class);
	    private int counter;
	    
	    /**
	     * Default constructor for FullTest class.
	     */
	    public FullTest() {
	        // You can initialize fields if needed
	    }

	    /**
	     * Method executed before any test methods in the suite.
	     */
	    @BeforeSuite
	    public void beforeSuite() {
	        logger.info("Before Suite: Initializing resources for the entire suite.");
	    }

	    /**
	     * Method executed before the first test method in the current class.
	     */
	    @BeforeClass
	    public void setUp() {
	        logger.info("Before Class: Setting up test class.");
	        counter = 0; // Initialize the counter
	    }

	    /**
	     * Method executed before each test method.
	     */
	    @BeforeMethod
	    public void init() {
	        logger.info("Before Method: Preparing for a test method.");
	        counter++; // Increment counter before each test
	    }

	    /**
	     * Test method to demonstrate logging and assertions.
	     */
	    @Test
	    public void testCounterIncrement() {
	        logger.info("Executing Test: testCounterIncrement.");
	        Assert.assertEquals(counter, 1, "Counter should be 1 after the first test.");
	        logger.info("Test passed: Counter incremented correctly.");
	    }

	    /**
	     * Another test method to demonstrate logging and assertions.
	     */
	    @Test
	    public void testCounterValue() {
	        logger.info("Executing Test: testCounterValue.");
	        Assert.assertEquals(counter, 2, "Counter should be 2 after the second test.");
	        logger.info("Test passed: Counter value verified.");
	    }

	    /**
	     * Method executed after all test methods in the current class have run.
	     */
	    @AfterClass
	    public void tearDown() {
	        logger.info("After Class: Cleaning up after tests.");
	    }

	    /**
	     * Method executed after all test methods in the suite have run.
	     */
	    @AfterSuite
	    public void afterSuite() {
	        logger.info("After Suite: Final cleanup for the entire suite.");
	    }

	
}
