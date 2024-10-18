package con.testng.testngpractise;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that contains test methods for various web applications.
 */
public class AssertionDemo {

    private static final Logger logger = LoggerFactory.getLogger(AssertionDemo.class);

    /**
     * Tests Google search functionality.
     * 
     * This test searches for "HYR Tutorials" on Google and asserts that the title
     * of the results page matches the expected title.
     * 
     * @throws Exception if there is an error during execution
     */
    @Test
    public void TestGoogle() throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("HYR Tutorials", Keys.ENTER);
        
        String expectedTitle = "HYR Tutorials - Google Search";
        String actualTitle = driver.getTitle();
        
        assertEquals(actualTitle, expectedTitle, "Title is mismatched");
        logger.info("Google search executed successfully. Expected title: '{}', Actual title: '{}'", expectedTitle, actualTitle);
        
        Thread.sleep(5000);
        driver.quit();
    }

    /**
     * Tests Facebook login functionality.
     * 
     * This test enters "HYR Tutorials" in the Facebook email field and prints
     * the title of the resulting page.
     * 
     * @throws Exception if there is an error during execution
     */
    @Test
    public void TestFacebook() throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
        driver.findElement(By.name("email")).sendKeys("HYR Tutorials", Keys.ENTER);
        
        String actualTitle = driver.getTitle();
        logger.info("Facebook page title: {}", actualTitle);
        
        Thread.sleep(5000);
        driver.quit();
    }
}
