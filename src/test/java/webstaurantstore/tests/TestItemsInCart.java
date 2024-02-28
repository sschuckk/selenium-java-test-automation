package webstaurantstore.tests;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;

import webstaurantstore.pageactions.Actions;

public class TestItemsInCart {
    private WebDriver driver;
    private Actions actions;
    // Create a logger instance.
    private final Logger logger = LogManager.getLogger(TestItemsInCart.class);

    @BeforeTest
    public void setUp() {
        logger.info("Starting Suite Setup!");
        String URL = "https://www.webstaurantstore.com/";

        driver = new ChromeDriver();
        driver.manage().window().maximize();      // Run in full size page
        driver.get(URL);
        actions = new Actions(driver);
    }

    // testCartItemAdd
    @Test(invocationCount = 1)
    public void testAddItemToCart(){
        actions.doSearchForProduct("ice");
        var item = actions.getFirstProductElement();
        actions.addCart(item);
        var notificationMessage = actions.getAddedToCartText();

        Assert.assertTrue(notificationMessage.contains("item added to your cart"));
        actions.closeNotification();

        // testCartButton
        actions.checkItemInCartAndGoToCart();


        // TODO: testCartQuantityUpdate
        var item_qty1 = actions.increaseItemQuantityBy(10);

        Assert.assertEquals(item_qty1, "11");

        var item_qty2 = actions.decreaseItemQuantityBy(9);
        Assert.assertEquals(item_qty2, "2");

        // TODO: testCartItemRemoval
        actions.removeItemFromCart();

        // TODO: testCartEmptyState
        System.out.println(actions.getEmptyCartText());
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
