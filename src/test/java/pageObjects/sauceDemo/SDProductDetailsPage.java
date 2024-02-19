package pageObjects.sauceDemo;

import base.BaseTest;
import org.openqa.selenium.By;
import utilities.LoggerUtils;

public class SDProductDetailsPage extends BaseTest {
    
    SDProductsPage productsPage = new SDProductsPage();

    //Buttons
    private final By addToCartButton = By.xpath("//button[contains(@id,'add-to-cart')]");
    private final By removeFromCartButton = By.xpath("//button[contains(@id,'remove')]");
    private final By backToProductsButton = By.id("back-to-products");

    //Labels
    private final By itemName = By.xpath("//div[contains(@class,'inventory_details_name')]");
    private final By itemPrice = By.xpath("//div[contains(@class,'inventory_details_price')]");

    public void addItemToCart() {
        LoggerUtils.info("Adding item to the cart");
        driver.findElement(addToCartButton).click();
    }

    public void removeItemFromCart() {
        LoggerUtils.info("Removing item from cart");
        driver.findElement(removeFromCartButton).click();
    }

    public void checkNumberOfItemsInCart(int n) {
        LoggerUtils.info("Checking number of items in cart");
        assert productsPage.numberOfItemsInCart() == n;
    }

    public String getItemName() {
        LoggerUtils.info("Getting name of current item");
        return driver.findElement(itemName).getText();
    }

    public String getItemPrice() {
        LoggerUtils.info("Getting price of current item");
        return driver.findElement(itemPrice).getText();
    }

    public void navigateBackToProducts() {
        LoggerUtils.info("Navigating back to product list");
        driver.findElement(backToProductsButton).click();
    }

}
