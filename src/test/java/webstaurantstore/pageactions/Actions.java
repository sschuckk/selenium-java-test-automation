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
        var homePage = new HomePage(webDriver);
        homePage.searchForProduct(product);
    }

    /**
     * Get the text of the search result on the search page.
     *
     * @return The text of the search result element.
     */
    public String getSearchResultText() {
        var searchPage = new SearchPage(webDriver);
        return searchPage.searchResultText();
    }

    public List<SearchPage> getProductLst() {
        var searchPage = new SearchPage(webDriver);
        var productList = searchPage.getAvailableProducts();
        Assert.assertNotEquals(0, productList.size(), "[EMPTY_LIST], empty product list, ");
        // TODO: Add logger msg
        return productList;
    }

    public SearchPage getFirstProductElement() {
        return getProductLst().getFirst();
    }

    public SearchPage getLastProductElement() {
        return getProductLst().getLast();
    }

    public void addCart(SearchPage item) {
        item.addToCart();
    }

    public String getAddedToCartText() {
        var searchPage = new SearchPage(webDriver);
        return searchPage.addedToCartNotification();
    }

    public void closeNotification() {
        var searchPage = new SearchPage(webDriver);
        searchPage.closeNotification();
    }

    public void checkItemInCartAndGoToCart() {
        var searchPage = new SearchPage(webDriver);
        searchPage.goToCart();
    }


    public String increaseItemQuantityBy(int qty) {
        var cartPage = new CartPage(webDriver);
        for(int i = 0; i < qty; i++) {
            cartPage.increaseItemQuantity();
        }
        return cartPage.getItemQuantity();
    }

    public String decreaseItemQuantityBy(int qty) {
        var cartPage = new CartPage(webDriver);
        for(int i = 0; i < qty; i++) {
            cartPage.decreaseItemQuantity();
        }
        return cartPage.getItemQuantity();
    }

    public void removeItemFromCart() {
        var cartPage = new CartPage(webDriver);
        cartPage.removeItem();
    }

    public String getEmptyCartText() {
        var cartPage = new CartPage(webDriver);
        return cartPage.getEmptyCartText();
    }
}
