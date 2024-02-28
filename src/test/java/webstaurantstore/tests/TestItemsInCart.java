package webstaurantstore.tests;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;

import webstaurantstore.pageactions.Actions;

import java.lang.reflect.Method;

/**
 * Test Suite to verify the functionality of cart page.
 */
public class TestItemsInCart {
    private WebDriver driver;
    private Actions actions;

    // Create a logger instance.
    private final Logger logger = LogManager.getLogger(TestItemsInCart.class);

    /**
     * Method to set up the test suite.
     */
    @BeforeTest
    public void setUp() {
        String URL = "https://www.webstaurantstore.com/";
        logger.info("Starting Suite Setup!");

        // Launch the browser and navigate to the application URL
        driver = new ChromeDriver();
        driver.manage().window().maximize();  // Run in full size page.
        driver.get(URL);

        // Initialize Actions class for performing interactions with page objects.
        actions = new Actions(driver);
    }


    /**
     * Method to log the name of the test case being executed.
     * @param method The test method being executed.
     */
    @BeforeMethod
    public void logTest(Method method) {
        logger.info("Starting Test: " + method.getName());
    }


    @Test(description = "Search for a product and add only first item to cart")
    public void testAddFirstProductToCart() {
        final var product = "refrigerator";

        actions.doSearchForProduct(product);
        final var item = actions.getFirstProduct();
        actions.addToCart(item);
        actions.closeNotification();

        // Verify if item description contains the name searched for
        Assert.assertTrue(actions.goToCartAndCheckItemDescription(product));
    }

    @Test(description = "Add a product to cart, update item quantity")
    public void testCartQuantityUpdate() {
        final var product = "refrigerator";

        actions.doSearchForProduct(product);
        final var item = actions.getFirstProduct();
        actions.addToCart(item);
        actions.closeNotification();
        actions.goToCartAndCheckItemDescription(product);

        // Increase the item quantity and verify if it displays the correct number of items.
        final var itemQtyIncreased = actions.increaseItemQuantityBy(10);
        Assert.assertEquals(itemQtyIncreased, "11");

        // Decrease the item quantity and verify if it displays the correct number of items.
        final var itemQtyDecreased = actions.decreaseItemQuantityBy(10);
        Assert.assertEquals(itemQtyDecreased, "1");
    }


    @Test(description = "Add a product to cart and then remove the product by clicking in the removal button")
    public void testCartItemRemoval() {
        final var product = "refrigerator";
        final var emptyCartMsg = "Your cart is empty.";

        actions.doSearchForProduct(product);
        final var item = actions.getFirstProduct();
        actions.addToCart(item);
        actions.closeNotification();
        actions.goToCartAndCheckItemDescription(product);
        actions.removeItemFromCart();

        // Check if the empty cart message is displayed.
        Assert.assertEquals(actions.getEmptyCartText(), emptyCartMsg);
    }

    @Test(description = "Add a product to cart and then empty all the cart by clicking in the empty button")
    public void testCartEmptyState() {
        final var product = "refrigerator";
        final var emptyCartMsg = "Your cart is empty.";

        actions.doSearchForProduct(product);
        final var item = actions.getFirstProduct();
        actions.addToCart(item);
        actions.closeNotification();
        actions.goToCartAndCheckItemDescription(product);
        actions.emptyTheCart();

        // Check if the empty cart message is displayed.
        Assert.assertEquals(actions.getEmptyCartText(), emptyCartMsg);
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
