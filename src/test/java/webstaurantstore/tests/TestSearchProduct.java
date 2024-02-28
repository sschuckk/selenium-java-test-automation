package webstaurantstore.tests;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.Assert;

import webstaurantstore.pageactions.Actions;

import java.lang.reflect.Method;


/**
 * Test Suite to verify the functionality of searching for a product.
 */
public class TestSearchProduct {
    private WebDriver driver;
    private Actions actions;

    // Create a logger instance.
    private final Logger logger = LogManager.getLogger(TestSearchProduct.class);

    /**
     * Setup method to initialize the WebDriver and navigate to the web page.
     */
    @BeforeTest
    public void setUp() {
        logger.info("Starting Suite Setup!");
        String URL = "https://www.webstaurantstore.com/";

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--headless");       // Run in headless mode

        // Create a new instance of ChromeDriver and navigate to the specified URL
        driver = new ChromeDriver(options);
        //driver.manage().window().maximize();      // Run in full size page
        driver.get(URL);
        actions = new Actions(driver);
    }

    /**
     * This method is executed before each test case just for debug purpose.2
     */
    @BeforeMethod
    public void logTest(Method method) {
        logger.info("Starting Test: " + method.getName());
    }

    /**
     * Define a small dataset for a data-driven testing technique.
     * Each row in the array represents a set of parameters to be passed to a test method using testNG.
     */
    @DataProvider(name = "valid-product")
    private Object[][] dpValid() {
        return new Object[][]{{"ice cream"}, {"coffee"}, {"amana"}};
    }

    @DataProvider(name = "invalid-product")
    private Object[][] dpInvalid() {
        return new Object[][]{{"~"}, {"@#$"}, {"1234567890"}};
    }

    /**
     * User Interface Test Cases.
     */
    @Test(dataProvider = "valid-product", description = "[TEST-ID:001] Search for a product")
    public void testSearchProduct(String product) {
        actions.doSearchForProduct(product);
        actions.getProductLst();
    }

    @Test(dataProvider = "valid-product", description = "[TEST-ID:002] Search for a product and check the product text result")
    public void testSearchResultText(String product) {
        actions.doSearchForProduct(product);
        var resultMessage = actions.getSearchResultText();

        Assert.assertTrue(resultMessage.contains(product));
    }

    @Test(dataProvider = "invalid-product", description = "[TEST-ID:003] Search for an invalid product and check for the 'Sorry' message")
    public void testSearchNotFound(String product) {
        actions.doSearchForProduct(product);
        var resultMessage = actions.getSearchResultText();

        Assert.assertTrue(resultMessage.contains("Sorry, we couldn't find any matches for"));
    }

    /**
     * Tear down method to close the WebDriver after running the tests.
     */
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Test Suite Stopped. WebDriver instance closed!");
        }
    }

}
