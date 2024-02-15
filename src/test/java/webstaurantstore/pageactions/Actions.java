package webstaurantstore.pageactions;

import org.openqa.selenium.WebDriver;

import webstaurantstore.pageobjects.pages.HomePage;
import webstaurantstore.pageobjects.pages.SearchPage;


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
        var homepage = new HomePage(webDriver);
        homepage.searchForProduct(product);
    }

    /**
     * Get the text of the search result on the search page.
     * @return The text of the search result element.
     */
    public String getSearchResultText() {
        var searchpage = new SearchPage(webDriver);
        return searchpage.SearchResultText();
    }

}
