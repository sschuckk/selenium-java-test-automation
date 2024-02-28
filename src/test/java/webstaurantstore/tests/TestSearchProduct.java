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
     * Method to set up the test suite.
     */
    @BeforeTest
    public void setUp() {
        String URL = "https://www.webstaurantstore.com/";
        logger.info("Starting Suite Setup!");

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");  // Run in incognito/private mode.

        // Launch the browser and navigate to the application URL
        driver = new ChromeDriver(options);
        //driver.manage().window().maximize();      // Run in full size page
        driver.get(URL);

        // Initialize Actions class for performing interactions with page objects.
        actions = new Actions(driver);
    }

    /**
     * Method to log the name of the test case being executed.
     *
     * @param method The test method being executed.
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

    @Test(dataProvider = "valid-product", description = "[TEST-ID:0S1] Verify that searching for a valid product returns at least one item.")
    public void testSearchProduct(String product) {
        actions.doSearchForProduct(product);

        // Check if the search for a product result in a list of product with at least 1 item.
        actions.getProductList();
    }

    @Test(dataProvider = "valid-product", description = "[TEST-ID:0S2] Search for a product and verify if the product name is displayed in result.")
    public void testSearchResultText(String product) {
        actions.doSearchForProduct(product);
        final var resultMessage = actions.getSearchResultText();

        // Verify that the searched product name is displayed in the search result after the search action.
        Assert.assertTrue(resultMessage.contains(product));
    }

    @Test(dataProvider = "invalid-product", description = "[TEST-ID:0S3] Search for an invalid product and verify the presence of the 'Sorry' as result.")
    public void testSearchNotFound(String product) {
        final var sorryMessage = "Sorry, we couldn't find any matches for";
        actions.doSearchForProduct(product);
        final var resultMessage = actions.getSearchResultText();

        // Verify that the sorryMessage is displayed after search for an invalid product.
        Assert.assertTrue(resultMessage.contains(sorryMessage));
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
