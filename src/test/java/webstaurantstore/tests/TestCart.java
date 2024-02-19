package webstaurantstore.tests;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.Assert;

import webstaurantstore.pageactions.Actions;
import webstaurantstore.pageobjects.pages.SearchPage;

public class TestCart {
    private WebDriver driver;
    private Actions actions;
    // Create a logger instance.
    private final Logger logger = LogManager.getLogger(TestCart.class);

    @BeforeTest
    public void setUp() {
        logger.info("Starting Suite Setup!");
        String URL = "https://www.webstaurantstore.com/";

        driver = new ChromeDriver();
        driver.get(URL);
        actions = new Actions(driver);
    }

    // testCartItemAdd
    @Test
    public void testCartItemAdd(){
        actions.doSearchForProduct("ice");
        var item = actions.getFirstProductElement();
        actions.addCart(item);
    }



    // testCartButton

    // testCartQuantityUpdate

    // testCartItemRemoval

    // testCartEmptyState


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
