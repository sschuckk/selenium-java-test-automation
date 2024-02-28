package webstaurantstore.pageobjects.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import webstaurantstore.tests.TestSearchProduct;

import java.time.Duration;


/**
 * Abstract base class to help with the test struct using Page Object Model.
 */
public abstract class BasePage {
    protected final SearchContext context;
    protected final FluentWait<SearchContext> fluentWait;
    private static final Logger logger = LogManager.getLogger(TestSearchProduct.class);

    /**
     * Constructor for BasePage class.
     *
     * @param context The search context used to locate elements on the page.
     */
    public BasePage(SearchContext context) {
        this.context = context;

        // Define the WebDriver fluent wait configuration
        this.fluentWait = new FluentWait<>(context)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);


        // Initialize any WebElement instances using PageFactory
        PageFactory.initElements(context, this);
    }

    /**
     * Checks if the specified element contains another element identified by the given locator.
     *
     * @param parentElement The parent WebElement to search within.
     * @param childLocator  The locator used to identify the child element.
     * @return true if the parent element contains the child element, false otherwise.
     */
    protected static boolean doesElementContain(WebElement parentElement, By childLocator) {
        try {
            parentElement.findElement(childLocator);
            return true;
        } catch (NoSuchElementException ex) {
            logger.debug("The child element was not found in parent element!");
            return false;
        }
    }

    /**
     * Waits until the specified WebElement is displayed and enabled. The wait time is specified in the fluentWait.
     *
     * @param element The WebElement to wait for.
     */
    protected void waitUntilElementIsDisplayed(WebElement element) {
        try {
            fluentWait.until(webDriver -> element.isDisplayed() && element.isEnabled());
        } catch (TimeoutException ex) {
            logger.debug("[TimeoutException] Timeout waiting for element to be present");
        }
    }
}