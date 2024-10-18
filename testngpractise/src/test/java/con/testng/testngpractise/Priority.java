package con.testng.testngpractise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * Test class for demonstrating TestNG priority  with SLF4J logging.
 */
public class Priority {

	/**
	 * Test class for demonstrating TestNG parameters with SLF4J logging.
	 */
	
	    private static final Logger logger = LoggerFactory.getLogger(Parameter.class);

	    /**
	     * Default constructor.
	     */
	    public Priority() {
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
	    @Test(priority = 1)
	    public void testLogin(@Optional("testUser") String username, 
	                          @Optional("testPassword") String password, 
	                          @Optional("http://defaulturl.com") String url) {
	        logger.info("Executing testLogin with parameters:");
	        logger.info("Username: {}", username);
	        logger.info("Password: {}", password);
	        logger.info("URL: {}", url);

	        // Simulate a login check (this is just an example).
	        assertEquals(username, "testUser", "Username does not match expected value.");
	        assertEquals(password, "testPassword", "Password does not match expected value.");

	        logger.info("Login test completed successfully.");
	    }

	    /**
	     * Test method for verifying login success.
	     */
	    @Test(priority = 2)
	    public void verifyLogin() {
	        logger.info("Verifying login...");
	        // Add verification logic here
	        assertEquals(true, true, "Login verification failed.");
	        logger.info("Login verification completed successfully.");
	    }

	    /**
	     * Test method for navigating to a specific page.
	     */
	    @Test(priority = 3)
	    public void navigateToPage() {
	        logger.info("Navigating to the specified page...");
	        // Add navigation logic here
	        logger.info("Navigation completed successfully.");
	    }
}
