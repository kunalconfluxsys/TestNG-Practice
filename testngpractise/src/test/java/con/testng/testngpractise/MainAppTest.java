package con.testng.testngpractise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
	/**
	 * Test class for the MainApp.
	 */
	public class MainAppTest {
	    private static final Logger logger = LoggerFactory.getLogger(MainAppTest.class);

	    /**
	     * Test method for the add function.
	     */
	    @Test(groups = "math")
	    public void testAdd() {
	        logger.info("Running testAdd");
	        int result = MainApp.add(5, 3);
	        logger.info("Result from add: {}", result);
	        Assert.assertEquals(result, 8, "5 + 3 should equal 8.");
	    }

	    /**
	     * Test method for adding negative numbers.
	     */
	    @Test(groups = "math")
	    public void testAddNegative() {
	        logger.info("Running testAddNegative");
	        int result = MainApp.add(-5, -3);
	        logger.info("Result from add: {}", result);
	        Assert.assertEquals(result, -8, "-5 + -3 should equal -8.");
	    }

	
}
