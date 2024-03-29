package pageObjects.sauceDemo;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.LoggerUtils;
import utilities.ReadConfig;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.function.Function;


public class SDProductsPage extends BaseTest {
    //Items
    private final By inventoryItem = By.className("inventory_item");
    private final By inventoryItemImg = By.xpath("//div[@class='inventory_item']/descendant::img");

    //Buttons
    private final By addToCartButton = By.xpath("//button[contains(@id,'add-to-cart')]");
    private final By removeFromCartButton = By.xpath("//button[contains(@id,'remove')]");
    private final By shoppingCartButton = By.id("shopping_cart_container");
    private final By itemName = By.className("inventory_item_name");
    private final By itemPrice = By.className("inventory_item_price");
    private final By hamburgerMenu = By.id("react-burger-menu-btn");
    private final By logoutButton = By.id("logout_sidebar_link");
    private final By openCartButon = By.className("shopping_cart_link");


    public void checkNumberOfItems(int size) {
        LoggerUtils.info("Checking that page contains " + size + " items");
        assert driver.findElements(inventoryItem).size() == size;
    }

    public void checkIfImgLinkContains(String link) {
        LoggerUtils.info("Checking if element link contains  " + link);
        assert driver.findElement(inventoryItemImg).getAttribute("src").contains(link);
    }

    public int numberOfItemsInCart() {
        LoggerUtils.info("Checking that page contains number of items in cart");
        try {
            return Integer.parseInt(driver.findElement(shoppingCartButton).findElement(By.xpath("descendant::span")).getText());
        } catch (Exception ignored) {
            return 0;
        }
    }

    public void addAllItemsToCart() {
        LoggerUtils.info("Adding all available items to the cart");
        int i = 0;
        for (WebElement element : driver.findElements(addToCartButton)) {
            element.click();
            i++;
            assert numberOfItemsInCart() == i;
        }
    }

    public void addNthItemToCart(int n) {
        LoggerUtils.info("Adding item #"+n+" to the cart");
        driver.findElements(inventoryItem).get(n - 1).findElement(By.xpath("descendant::button[contains(@id,'add-to-cart')]")).click();
    }

    public void removeNthItemToCart(int n) {
        LoggerUtils.info("Removing item #"+n+" from the cart");
        driver.findElements(inventoryItem).get(n - 1).findElement(By.xpath("descendant::button[contains(@id,'remove')]")).click();
    }

    public void removeAllItemsFromCart() {
        LoggerUtils.info("removing all available items from the cart");
        int i = numberOfItemsInCart();
        for (WebElement element : driver.findElements(removeFromCartButton)) {
            element.click();
            i--;
            assert numberOfItemsInCart() == i;
        }
    }

    public String getNthItemName(int n) {
        LoggerUtils.info("Getting name of item #" + n);
        return driver.findElements(itemName).get(n - 1).getText();
    }

    public void openNthItem(int n){
        LoggerUtils.info("Opening details page for item #" + n);
        driver.findElements(itemName).get(n - 1).click();
    }

    public String getNthItemPrice(int n) {
        LoggerUtils.info("Getting price of item #" + n);
        return driver.findElements(itemPrice).get(n - 1).getText();
    }

    public void logout() throws InterruptedException {
        LoggerUtils.info("Log out");
        driver.findElement(hamburgerMenu).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));

        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logout.click();
    }

    public void openCart(){
        LoggerUtils.info("Opening cart");
        driver.findElement(openCartButon).click();
    }
}
