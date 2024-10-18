package con.testng.testngpractise;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains test cases for verifying functionalities on Google and Facebook
 * using Selenium WebDriver and the TestNG framework with soft assertions.
 */


public class SoftAssertions {
	
	/**
	 * This class contains test cases for Google and Facebook using Selenium WebDriver
	 * and the TestNG framework with soft assertions.
	 */
	
	    private static final Logger logger = LoggerFactory.getLogger(SoftAssertions.class);
	    
	    /**
	     * Default constructor for SoftAssertions class.
	     * Initializes the SoftAssertions instance and logs the creation of the instance.
	     */
	    public SoftAssertions() {
	        logger.info("SoftAssertions instance created");
	    }

	    /**
	     * Test case to search for "HYR Tutorials" on Google.
	     * 
	     * <p>
	     * This test opens Google, enters a search term, and verifies that the page title 
	     * matches the expected value. It also includes a delay to allow observation of the results.
	     * </p>
	     *
	     * @throws Exception if there is an issue with WebDriver setup or navigation.
	     */
	    @Test
	    public void testGoogle() throws Exception {
	        WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();

	        logger.info("Navigating to Google");
	        driver.get("https://www.google.com/");
	        driver.findElement(By.name("q")).sendKeys("HYR Tutorials", Keys.ENTER);

	        String expectedTitle = "HYR Tutorials - Google Search";
	        String actualTitle = driver.getTitle();
	        logger.info("Verifying title for Google search");
	        assertEquals(actualTitle, expectedTitle, "Title is mismatched");

	        Thread.sleep(5000); // Allow time to observe the result
	        driver.quit();
	    }

	    /**
	     * Test case to verify login behavior on Facebook.
	     * 
	     * <p>
	     * This test opens Facebook, enters a value in the email field, and verifies various 
	     * attributes including title, URL, text input, border style, and error message.
	     * It uses soft assertions to ensure that all conditions are checked even if one fails.
	     * </p>
	     *
	     * @throws Exception if there is an issue with WebDriver setup or navigation.
	     */
	    @Test
	    public void testFacebook() throws Exception {
	        WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();

	        logger.info("Navigating to Facebook");
	        driver.get("https://www.facebook.com/");
	        driver.findElement(By.name("email")).sendKeys("HYR Tutorials", Keys.ENTER);

	        SoftAssert softAssert = new SoftAssert();
	        
	        // Title Assertion
	        String actualTitle = driver.getTitle();
	        String expectedTitle = "Log in to Facebook";
	        logger.info("Verifying title for Facebook login");
	        softAssert.assertEquals(actualTitle, expectedTitle, "Title is mismatched");
	        
	        // URL Assertion
	        String actualUrl = driver.getCurrentUrl();
	        String expectedUrl = "https://www.facebook.com/";
	        logger.info("Verifying URL for Facebook login");
	        softAssert.assertEquals(actualUrl, expectedUrl, "URL is mismatched");
	        
	        // Text Assertion
	        String actualText = driver.findElement(By.name("email")).getAttribute("value");
	        String expectedText = "";
	        logger.info("Verifying entered username text");
	        softAssert.assertEquals(actualText, expectedText, "Username text is mismatched");
	        
	        // Border Assertion
	        String actualBorder = driver.findElement(By.name("email")).getCssValue("border");
	        String expectedBorder = "1px solid rgb(240, 40, 73)";
	        logger.info("Verifying border style for email input");
	        softAssert.assertEquals(actualBorder, expectedBorder, "Username border is mismatched");
	        
	        // Error Message Assertion
	        String actualErrorMessage = driver.findElement(By.xpath("(//div[@id='email_container']/div)[last()]")).getText();
	        String expectedErrorMessage = "The email address or mobile number you entered isn't connected to an account. Find your account and log in.";
	        logger.info("Verifying error message for email input");
	        softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Username error message is mismatched");
	        
	        driver.quit();
	        softAssert.assertAll();
	    }
	
}
