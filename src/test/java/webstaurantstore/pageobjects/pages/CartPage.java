package webstaurantstore.pageobjects.pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

/**
 * Page class representing the Cart page.
 */
public class CartPage extends BasePage {

    // Locators for elements present on the page: https://www.webstaurantstore.com/cart/
    @FindBy(xpath = "//a[@data-testid='cart-button']")
    WebElement goToCartButton;

    @FindBy(css = ".itemDescription.description.overflow-visible")
    WebElement itemDescription;

    @FindBy(xpath = "//input[@class='quantityInput input-mini']")
    WebElement itemQuantityInput;

    @FindBy(xpath = "//button[@title='Increase Quantity']")
    WebElement quantityIncreaseButton;

    @FindBy(xpath = "//button[@title='Decrease Quantity']")
    WebElement quantityDecreaseButton;

    @FindBy(css = ".deleteCartItemButton")
    WebElement removeItemButton;

    @FindBy(css = ".emptyCartButton")
    WebElement emptyCartButton;

    @FindBy(css = ".header-1")
    WebElement emptyCartMessage;

    @FindBy(xpath = "//footer[@data-testid='modal-footer']//button[@type='button'][1]")
    WebElement confirmEmptyCartButton;

    /**
     * Constructor for CartPage class.
     *
     * @param context The search context used to locate elements on the cart page.
     */
    public CartPage(SearchContext context) {
        super(context);
    }

    public void goToCart() {
        waitUntilElementIsDisplayed(goToCartButton);
        goToCartButton.click();
    }

    public String getItemDescription() {
        // Designed to work only with one item in cart
        return itemDescription.getText();
    }

    public String getItemQuantity() {
        // Designed to work only with one in cart
        return itemQuantityInput.getAttribute("value");
    }

    public void increaseItemQuantity() {
        quantityIncreaseButton.click();
    }

    public void decreaseItemQuantity() {
        quantityDecreaseButton.click();
    }

    public void removeOneItem() {
        removeItemButton.click();
    }

    public void emptyCart() {
        emptyCartButton.click();
        waitUntilElementIsDisplayed(confirmEmptyCartButton);
        confirmEmptyCartButton.click();
    }

    public String getEmptyCartText() {
        waitUntilElementIsDisplayed(emptyCartMessage);
        return emptyCartMessage.getText();
    }

}
