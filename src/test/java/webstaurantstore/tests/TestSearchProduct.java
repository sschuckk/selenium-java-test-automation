package webstaurantstore.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;

import webstaurantstore.pageactions.Actions;



/**
 * Test Suite to verify the functionality of searching for a product.
 */
public class TestSearchProduct {
    WebDriver driver;
    ChromeOptions options;
    private Actions actions;

    // Create a logger instance.
    private static final Logger logger = LogManager.getLogger(TestSearchProduct.class);

    /**
     * Setup method to initialize the WebDriver and navigate to the web page.
     */
    @BeforeTest
    void setUp() {
        logger.info("Starting setup!");
        String URL = "https://www.webstaurantstore.com/";

        // Configure Chrome options
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Create a new instance of ChromeDriver and navigate to the specified URL
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(URL);
        actions = new Actions(driver);
    }

    @Test(description = "[TEST-ID:001] Search for a product")
    public void testSearchProduct() {
        actions.doSearchForProduct("icecream");
    }

    @Test(description = "[TEST-ID:002] Check the product text result")
    public void testSearchResultText() {
        String result = actions.getSearchResultText();
        Assert.assertTrue(result.contains("icecream"));
    }


    /**
     * Tear down method to quit the WebDriver after tests execution.
     */
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver closed!");
        }
    }
}