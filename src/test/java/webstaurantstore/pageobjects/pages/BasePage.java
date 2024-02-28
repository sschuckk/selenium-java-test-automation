package webstaurantstore.pageobjects.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.Thread;

import java.time.Duration;


/**
 * Abstract base class to help with the test struct using Page Object Model.
 */
public abstract class BasePage {
    final protected SearchContext context;
    final protected FluentWait<SearchContext> fluentWait;

    public BasePage(SearchContext context) {
        // Define the WebDriver fluent wait configuration
        this.context = context;
        this.fluentWait = new FluentWait<>(context)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);


        // Initialize any WebElement instances using PageFactory
        // NullPointerExceptions can be thrown if you make the assumption that the fields are already initialized.
        PageFactory.initElements(context, this);
    }

    /**
     * Checks if the specified element contains another element identified by the given locator.
     *
     * @param parentElement The parent WebElement to search within.
     * @param childLocator  The locator used to identify the child element.
     * @return true if the parent element contains the child element, false otherwise.
     */
    protected static boolean doesElementContains(WebElement parentElement, By childLocator) {
        try {
            parentElement.findElement(childLocator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected void waitUntilElementIsDisplayed(WebElement element) {
        try {
            fluentWait.until(webDriver -> element.isDisplayed() && element.isEnabled());
        } catch (TimeoutException ex) {
            System.out.println("Timeout waiting for element to be removed from DOM");
            // TODO: Add logging
        }
    }
}