package pageObjects;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.ReadConfig;

public class SDProductsPage extends BaseTest {
    ReadConfig rc = new ReadConfig();
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


    public void checkNumberOfItems(int size) {
        assert driver.findElements(inventoryItem).size() == size;
    }

    public void checkIfImgLinkContains(String link) {
        assert driver.findElement(inventoryItemImg).getAttribute("src").contains(link);
    }

    public int numberOfItemsInCart() {
        try {
            return Integer.parseInt(driver.findElement(shoppingCartButton).findElement(By.xpath("descendant::span")).getText());
        } catch (Exception ignored) {
            return 0;
        }
    }

    public void addAllItemsToCart() {
        int i = 0;
        for (WebElement element : driver.findElements(addToCartButton)) {
            element.click();
            i++;
            assert numberOfItemsInCart() == i;
        }
    }

    public void addNthItemToCart(int n) {
        driver.findElements(inventoryItem).get(n - 1).findElement(By.xpath("descendant::button[contains(@id,'add-to-cart')]")).click();
    }

    public void removeNthItemToCart(int n) {
        driver.findElements(inventoryItem).get(n - 1).findElement(By.xpath("descendant::button[contains(@id,'remove')]")).click();
    }

    public void removeAllItemsFromCart() {
        int i = numberOfItemsInCart();
        for (WebElement element : driver.findElements(removeFromCartButton)) {
            element.click();
            i--;
            assert numberOfItemsInCart() == i;
        }
    }

    public String getNthItemName(int n){
        return driver.findElements(itemName).get(n - 1).getText();
    }

    public String getNthItemPrice(int n){
        return driver.findElements(itemPrice).get(n - 1).getText();
    }

    public void logout() throws InterruptedException {
        driver.findElement(hamburgerMenu).click();
        Thread.sleep(400);
        driver.findElement(logoutButton).click();
    }
}
