package webstaurantstore.pageobjects.pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * This class represents the Search result page of the Webstaurantstore application and partial contains the locators
 * and methods to interact with elements on the Search page.
 * Uses Page Factory Model to initialize WebElement instances.
 */
public class SearchPage extends BasePage{

    @FindBy(xpath = "(//div[@class='search__wrap'])[1]")
    WebElement SearchWrap;

    /**
     * Constructor to initialize the SearchPage object.
     * @param context The search context (WebDriver) used to locate elements.
     */
    public SearchPage(SearchContext context) {
        super(context);     // Calls the constructor of the superclass
    }

    /**
     * Method to get the text of the search result wrapper element.
     * @return The text of the search result wrapper.
     */
    public String SearchWrapText () {
        wait.until(element -> SearchWrap.isDisplayed());      // Use lambda expression to wait until the search wrap element is displayed
        return SearchWrap.getText();
    }
}