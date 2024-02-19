package webstaurantstore.pageactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import webstaurantstore.pageobjects.pages.HomePage;
import webstaurantstore.pageobjects.pages.SearchPage;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;


/**
 * Defines the actions that can be performed on the web pages.
 */
public class Actions {
    private final WebDriver webDriver;

    /**
     * Constructor to initialize the WebDriver obj.
     * @param webDriver WebDriver object used for interactions with web pages.
     */
    public Actions(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Perform a search for a product on the home page.
     * @param product The product to search for.
     */
    public void doSearchForProduct (String product) {
        var homePage = new HomePage(webDriver);
        homePage.searchForProduct(product);
    }

    /**
     * Get the text of the search result on the search page.
     * @return The text of the search result element.
     */
    public String getSearchResultText() {
        var searchPage = new SearchPage(webDriver);
        return searchPage.SearchResultText();
    }


    public List<SearchPage> getProductLst() {
        var searchPage = new SearchPage(webDriver);
        var productList = searchPage.getAvailableProducts();
        Assert.assertNotEquals(0, productList.size(), "[EMPTY_LIST], empty product list, ");
//          TODO: Add logger msg
        return productList;
    }

    public SearchPage getFirstProductElement() {
        return getProductLst().getFirst();
    }

    public SearchPage getLastProductElement() {
        return getProductLst().getLast();
    }

    public void addCart (SearchPage item) {
        item.addToCart();
    }


}
