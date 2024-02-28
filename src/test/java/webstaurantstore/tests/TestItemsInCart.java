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
        // Run in full size page.
        driver.manage().window().maximize();
        driver.get(URL);
        actions = new Actions(driver);
    }

    @Test(description = "Search for a product and add only first item to cart")
    public void testAddFirstProductToCart() {
        final var product = "refrigerator";

        actions.doSearchForProduct(product);
        var item = actions.getFirstProduct();
        actions.addToCart(item);
        actions.closeNotification();

        // Verify if item description contains the name searched for
        Assert.assertTrue(actions.goToCartAndCheckItemDescription(product));
    }

    @Test(description = "Add a product to cart, update item quantity")
    public void testCartQuantityUpdate() {
        final var product = "refrigerator";

        actions.doSearchForProduct(product);
        var item = actions.getFirstProduct();
        actions.addToCart(item);
        actions.closeNotification();
        actions.goToCartAndCheckItemDescription(product);

        // Increase the item quantity and verify if it displays the correct number of items.
        var itemQtyIncreased = actions.increaseItemQuantityBy(10);
        Assert.assertEquals(itemQtyIncreased, "11");

        // Decrease the item quantity and verify if it displays the correct number of items.
        var itemQtyDecreased = actions.decreaseItemQuantityBy(10);
        Assert.assertEquals(itemQtyDecreased, "1");
    }


    @Test(description = "Add a product to cart and then remove the product by clicking in the removal button")
    public void testCartItemRemoval() {
        final var product = "refrigerator";
        final var emptyCartMsg = "Your cart is empty.";

        actions.doSearchForProduct(product);
        var item = actions.getFirstProduct();
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
        var item = actions.getFirstProduct();
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
