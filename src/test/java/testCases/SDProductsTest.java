package testCases;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.SDLoginPage;
import pageObjects.SDProductsPage;
import utilities.ReadConfig;

public class SDProductsTest extends BaseTest {
    SDLoginPage loginPage;
    SDProductsPage productsPage;
    ReadConfig rc = new ReadConfig();

    @BeforeTest
    public void login() {
        loginPage = new SDLoginPage();
        productsPage = new SDProductsPage();
        loginPage.openPage();
        loginPage.login(rc.getTestData("standard_user"), rc.getTestData("password"));
    }

    @DataProvider(name = "itemInfo")
    public static Object[][] getTestData() {
        return new Object[][]{
                {1, "Sauce Labs Backpack", "$29.99"},
                {2, "Sauce Labs Bike Light", "$9.99"},
                {3, "Sauce Labs Bolt T-Shirt", "$15.99"},
                {4, "Sauce Labs Fleece Jacket", "$49.99"},
                {5, "Sauce Labs Onesie", "$7.99"},
                {6, "Test.allTheThings() T-Shirt (Red)", "$15.99"}};
    }

    @Test(description = "Adding all items to cart", priority = 2)
    public void addAllItemsToCart() {
        productsPage.addAllItemsToCart();
    }

    @Test(description = "Removing all items from cart", priority = 2)
    public void removeAllItemsFromCart() {
        productsPage.removeAllItemsFromCart();
    }

    @Test(dataProvider = "itemInfo", description = "Adding items to Cart", priority = 1)
    public void addAndRemoveItem(int itemNumber, String itemName, String itemPrice) {
        productsPage.addNthItemToCart(itemNumber);
        assert productsPage.numberOfItemsInCart() == 1;
        productsPage.removeNthItemToCart(itemNumber);
        assert productsPage.numberOfItemsInCart() == 0;
    }

    @Test(dataProvider = "itemInfo", description = "Checking item name", priority = 3)
    public void checkItemName(int itemNumber, String itemName, String itemPrice) {
        assert productsPage.getNthItemName(itemNumber).equals(itemName);
    }

    @Test(dataProvider = "itemInfo", description = "Checking item price", priority = 3)
    public void checkItemPrice(int itemNumber, String itemName, String itemPrice) {
        assert productsPage.getNthItemPrice(itemNumber).equals(itemPrice);
    }
}
