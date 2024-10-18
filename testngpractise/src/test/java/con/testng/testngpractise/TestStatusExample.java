package con.testng.testngpractise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * Test class for demonstrating enabling and disabling tests in TestNG.
 */
public class TestStatusExample {

	
	
	    private static final Logger logger = LoggerFactory.getLogger(TestStatusExample.class);

	    /**
	     * Default constructor.
	     */
	    public TestStatusExample() {
	        // Default constructor
	    }

	    /**
	     * Test method that accepts parameters for username, password, and URL.
	     *
	     * @param username the username to log in
	     * @param password the password to log in
	     * @param url      the URL of the application
	     */
	    @Parameters({"username", "password", "url"})
	    @Test(priority = 1, enabled = true)
	    public void testLogin(@Optional("defaultUser") String username, 
	                          @Optional("defaultPassword") String password, 
	                          @Optional("http://defaulturl.com") String url) {
	        logger.info("Executing testLogin with parameters:");
	        logger.info("Username: {}", username);
	        logger.info("Password: {}", password);
	        logger.info("URL: {}", url);

	        // Simulate a login check
	        assertEquals(username, "testUser", "Username does not match expected value.");
	        assertEquals(password, "testPassword", "Password does not match expected value.");

	        logger.info("Login test completed successfully.");
	    }

	    /**
	     * Test method for verifying login success.
	     */
	    @Test(priority = 2, enabled = false)
	    public void verifyLogin() {
	        logger.info("Verifying login...");
	        // Add verification logic here
	        assertEquals(true, true, "Login verification failed.");
	        logger.info("Login verification completed successfully.");
	    }

	    /**
	     * Test method for navigating to a specific page.
	     */
	    @Test(priority = 3, enabled = true)
	    public void navigateToPage() {
	        logger.info("Navigating to the specified page...");
	        // Add navigation logic here
	        logger.info("Navigation completed successfully.");
	    }
	
}
