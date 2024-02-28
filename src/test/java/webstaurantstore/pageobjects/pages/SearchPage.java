package webstaurantstore.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;


/**
 * Represents the search result page of the Webstaurantstore application.
 * This class contains locators and methods to interact with elements on the search page.
 * It utilizes the Page Factory Model for initializing WebElement instances.
 */
public class SearchPage extends BasePage {

    @FindBy(className = "page-header")
    WebElement pageHeaderResult;

    @FindBy(id = "ProductBoxContainer")
    List<WebElement> productBoxContainer;

    @FindBy(name = "addToCartButton")
    WebElement addToCartButton;

    @FindBy(className = "notification__heading")
    WebElement notificationAddedToCart;

    @FindBy(xpath = "//p[@class='msg']//button[@type='button']")
    WebElement closeCartNotification;

    @FindBy(xpath = "//div[@data-role='notification']")
    WebElement popUp;

    /**
     * Constructor to initialize the SearchPage object.
     *
     * @param context The search context (WebDriver) used to locate elements.
     */
    public SearchPage(SearchContext context) {
        super(context);     // Calls the constructor of the superclass
    }

    /**
     * Creates a new SearchPage object using the provided WebElement.
     */
    public static SearchPage fromWebElement(WebElement element) {
        return new SearchPage(element);
    }

    /**
     * Method to get the text of the search result wrapper element.
     *
     * @return The text of the search result wrapper.
     */
    public String searchResultText() {
        fluentWait.until(element -> pageHeaderResult.isDisplayed());
        return pageHeaderResult.getText();
    }

    /**
     * Retrieves the list of available products from the product container.
     *
     * @return A list of SearchPage objects representing the available products only.
     */
    public List<SearchPage> getAvailableProducts() {
        List<SearchPage> availableProducts = new ArrayList<>();
        for (WebElement productElement : productBoxContainer) {
            if (doesElementContains(productElement, By.name("addToCartButton"))) {
                SearchPage searchPage = SearchPage.fromWebElement(productElement);
                availableProducts.add(searchPage);
            }
        }
        return availableProducts;
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public String addedToCartNotification() {
        fluentWait.until(element -> notificationAddedToCart.isDisplayed());
        return notificationAddedToCart.getText();
    }

    public void closeNotification() {
        closeCartNotification.click();
        // TODO: Create a method do verify when a element fade-out
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
