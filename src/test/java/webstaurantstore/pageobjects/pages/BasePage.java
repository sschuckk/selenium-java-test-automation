package webstaurantstore.pageobjects.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;


/**
 * Abstract base class to help with the test struct using Page Object Model.
 */
public abstract class BasePage {
    final protected SearchContext context;
    final protected FluentWait<SearchContext> wait;

    /**
     * Constructor to initialize the BasePage object.
     * @param context The search context (WebDriver) used to locate elements.
     */
    public BasePage(SearchContext context)
    {
        this.context = context;
        this.wait = new FluentWait<>(context)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        // Initialize any WebElement instances using PageFactory
        // NullPointerExceptions can be thrown if you make the assumption that the fields are already initialized.
        PageFactory.initElements(context, this);
    }
}