package webstaurantstore.pageactions;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import webstaurantstore.pageobjects.pages.HomePage;
import webstaurantstore.pageobjects.pages.SearchPage;
import webstaurantstore.pageobjects.pages.CartPage;

import java.util.List;


/**
 * Defines the actions that can be performed on the web pages.
 */
public class Actions {
    private final WebDriver webDriver;

    /**
     * Constructor to initialize the WebDriver obj.
     *
     * @param webDriver WebDriver object used for interactions with web pages.
     */
    public Actions(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Perform a search for a product on the home page.
     *
     * @param product The product to search for.
     */
    public void doSearchForProduct(String product) {
        final HomePage homePage = new HomePage(webDriver);
        homePage.searchForProduct(product);
    }

    /**
     * Get the text of the search result on the search page.
     *
     * @return The text of the search result element.
     */
    public String getSearchResultText() {
        final SearchPage searchPage = new SearchPage(webDriver);
        return searchPage.searchResultText();
    }

    /**
     * Retrieves a list of products from the search page.
     *
     * @return The list of products available on the search page.
     * @throws AssertionError If the product list is empty.
     */
    public List<SearchPage> getProductList() {
        final SearchPage searchPage = new SearchPage(webDriver);
        final List<SearchPage> productList = searchPage.getAvailableProducts();

        // Check that the list is not empty
        Assert.assertNotEquals(0, productList.size(), "[EMPTY_LIST] The product list is empty. Please verify the product name.");
        return productList;
    }

    public SearchPage getFirstProduct() {
        return getProductList().getFirst();
    }

    public SearchPage getLastProductElement() {
        return getProductList().getLast();
    }

    public void addToCart(SearchPage item) {
        item.addToCart();
    }

    public String getAddedToCartText() {
        final SearchPage searchPage = new SearchPage(webDriver);
        return searchPage.addedToCartNotification();
    }

    public void closeNotification() {
        final SearchPage searchPage = new SearchPage(webDriver);
        searchPage.closeNotification();
    }

    /**
     * Navigates to the cart page and checks if the item description contains the specified item.
     *
     * @param item The item to check in the item description.
     * @return True if the item description contains the specified item; false otherwise.
     */
    public boolean goToCartAndCheckItemDescription(String item) {
        CartPage cartPage = new CartPage(webDriver);
        cartPage.goToCart();
        String itemDescription = cartPage.getItemDescription();
        return itemDescription.toLowerCase().contains(item.toLowerCase());
    }

    /**
     * Increases the quantity of the item in the cart by the specified quantity.
     *
     * @param qty The quantity by which to increase the item quantity.
     * @return The updated quantity of the item in the cart.
     */
    public String increaseItemQuantityBy(int qty) {
        final CartPage cartPage = new CartPage(webDriver);
        for (int i = 0; i < qty; i++) {
            cartPage.increaseItemQuantity();
        }
        return cartPage.getItemQuantity();
    }

    /**
     * Decreases the quantity of the item in the cart by the specified quantity.
     *
     * @param qty The quantity by which to decrease the item quantity.
     * @return The updated quantity of the item in the cart.
     */
    public String decreaseItemQuantityBy(int qty) {
        final CartPage cartPage = new CartPage(webDriver);
        for (int i = 0; i < qty; i++) {
            cartPage.decreaseItemQuantity();
        }
        return cartPage.getItemQuantity();
    }

    public void removeItemFromCart() {
        final CartPage cartPage = new CartPage(webDriver);
        cartPage.removeOneItem();
    }

    public void emptyTheCart() {
        final CartPage cartPage = new CartPage(webDriver);
        cartPage.emptyCart();
    }

    public String getEmptyCartText() {
        final CartPage cartPage = new CartPage(webDriver);
        return cartPage.getEmptyCartText();
    }
}
