package con.testng.testngpractise;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains test methods for logging into the OrangeHRM application and verifying user information.
 */

public class OrangeHRM {

    private static final Logger logger = LoggerFactory.getLogger(OrangeHRM.class);
    WebDriver driver;

    /**
     * Launches the OrangeHRM application.
     */
    @Test
    public void LaunchApp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        logger.info("Launched the OrangeHRM application.");
    }

    /**
     * Enters login credentials and logs into the application.
     */
    @Test(dependsOnMethods = "LaunchApp")
    public void EnterLoginDetails() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
        logger.info("Entered login details and clicked login.");
    }

    /**
     * Navigates to the 'My Info' section after logging in.
     */
    @Test(dependsOnMethods = "EnterLoginDetails")
    public void NavigateToMyInfo() {
        driver.findElement(By.id("menu_pim_viewMyDetails")).click();
        logger.info("Navigated to 'My Info' section.");
    }

    /**
     * Verifies the display of 'My Info' section.
     */
    @Test(dependsOnMethods = "NavigateToMyInfo")
    public void VerifyMyInfo() {
        boolean isDisplayed = driver.findElement(By.id("employee-details")).isDisplayed();
        logger.info("'My Info' section displayed: {}", isDisplayed);
        driver.quit();
    }

    /**
     * Verifies the login by checking the presence of the welcome message.
     */
    @Test(dependsOnMethods = "EnterLoginDetails")
    public void VerifyLogin() {
        WebElement element = driver.findElement(By.id("welcome"));
        boolean isDisplayed = element.isDisplayed();
        logger.info("Welcome message displayed: {}", isDisplayed);
        logger.info("Welcome message text: {}", element.getText());
        driver.quit();
    }
}