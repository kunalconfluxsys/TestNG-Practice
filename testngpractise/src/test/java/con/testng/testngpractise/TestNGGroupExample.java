package con.testng.testngpractise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * This class demonstrates the usage of TestNG with multiple test groups.
 * The tests are categorized into different groups: "smoke", "regression", and "integration".
 */

	public class TestNGGroupExample {

	    private static final Logger logger = LoggerFactory.getLogger(TestNGGroupExample.class);
	    private int sampleValue;

	    /**
	     * Setup method that runs before any tests in this class.
	     */
	    @BeforeClass
	    public void setUp() {
	        logger.info("Setting up the test environment...");
	        // Initialization code here
	        sampleValue = 42; // Sample value for tests
	    }

	    /**
	     * Test method for smoke tests.
	     */
	    @Test(groups = "smoke")
	    public void testSmoke1() {
	        logger.info("Running smoke test 1");
	        Assert.assertEquals(sampleValue, 42, "Sample value should be 42.");
	    }

	    /**
	     * Another smoke test method.
	     */
	    @Test(groups = "smoke")
	    public void testSmoke2() {
	        logger.info("Running smoke test 2");
	        Assert.assertTrue(sampleValue > 0, "Sample value should be positive.");
	    }

	    /**
	     * Test method for regression tests.
	     */
	    @Test(groups = "regression")
	    public void testRegression1() {
	        logger.info("Running regression test 1");
	        Assert.assertNotNull(sampleValue, "Sample value should not be null.");
	        Assert.assertTrue(sampleValue < 100, "Sample value should be less than 100.");
	    }

	    /**
	     * Test method for integration tests.
	     */
	    @Test(groups = "integration")
	    public void testIntegration1() {
	        logger.info("Running integration test 1");
	        // Simulating a basic integration logic
	        int calculatedValue = sampleValue + 10;
	        Assert.assertEquals(calculatedValue, 52, "Calculated value should be 52.");
	    }

	    /**
	     * Cleanup method that runs after all tests in this class.
	     */
	    @AfterClass
	    public void tearDown() {
	        logger.info("Tearing down the test environment...");
	        // Cleanup code here
	        sampleValue = 0; // Resetting for cleanup
	        logger.info("Reset sampleValue to: {}", sampleValue);
	    }
	
	
	
	
	
}
