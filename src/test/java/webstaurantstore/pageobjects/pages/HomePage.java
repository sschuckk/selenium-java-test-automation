package webstaurantstore.pageobjects.pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * This class represents the Home Page of the Webstaurantstore application and partial contains the locators
 * and methods to interact with elements on the Home Page.
 * Uses Page Factory Model to initialize WebElement instances.
 */
public class HomePage extends BasePage {

    // Locators for elements present on the page: https://www.webstaurantstore.com/
    @FindBy(id = "searchval")
    WebElement SearchInput;

    @FindBy(css = "#searchForm button[type = 'submit']")
    WebElement SearchButton;

    /**
     * Constructor to initialize the HomePage object.
     *
     * @param context The search context (WebDriver) used to locate elements.
     */
    public HomePage(SearchContext context) {
        super(context);     // Calls the constructor of the superclass
    }

    /**
     * Method to perform a search for a product on the HomePage.
     *
     * @param product The product to search for.
     */
    public void searchForProduct(String product) {
        SearchInput.clear();
        SearchInput.sendKeys(product);
        SearchButton.click();
    }

}

