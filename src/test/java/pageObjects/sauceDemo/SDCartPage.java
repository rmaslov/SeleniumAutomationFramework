package pageObjects.sauceDemo;

import base.BaseTest;
import org.openqa.selenium.By;
import utilities.LoggerUtils;

public class SDCartPage extends BaseTest {

    //Buttons
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By removeFromCartButton = By.xpath("//button[contains(@id,'remove')]");
    private final By checkoutButton = By.id("checkout");

    //Labels
    private final By productName = By.className("inventory_item_name");
    private final By priceLabel = By.className("inventory_item_price");
    private final By quantityLabel = By.className("cart_quantity");
    private final By item = By.className("cart_item");

    public void checkNumberOfItems(int size) {
        LoggerUtils.info("Checking that cart contains " + size + " items");
        assert driver.findElements(item).size() == size;
    }

    public void removeItemFromCart(int n) {
        LoggerUtils.info("Removing item #" + n + " from cart");
        driver.findElements(removeFromCartButton).get(n-1).click();
    }

    public void returnToShopping() {
        LoggerUtils.info("Navigating back to Products page");
        driver.findElement(continueShoppingButton).click();
    }

    public void checkQuantityOfItem(int n, int quantity) {
        int number = n - 1;
        LoggerUtils.info("Checking that cart item #" + n + " has [" + quantity + "] quantity");
        assert driver.findElements(item).get(number).findElement(quantityLabel).getText().equals(String.valueOf(quantity));
    }

    public void checkPriceOfItem(int n, String price) {
        int number = n - 1;
        LoggerUtils.info("Checking that cart item #" + n + " has [" + price + "] price");
        assert driver.findElements(item).get(number).findElement(priceLabel).getText().equals(price);
    }

    public void checkNameOfItem(int n, String name) {
        int number = n - 1;
        LoggerUtils.info("Checking that cart item #" + n + " has [" + name + "] name");
        assert driver.findElements(item).get(number).findElement(productName).getText().equals(name);
    }

}
