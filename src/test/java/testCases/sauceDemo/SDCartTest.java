package testCases.sauceDemo;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.sauceDemo.SDCartPage;
import pageObjects.sauceDemo.SDLoginPage;
import pageObjects.sauceDemo.SDProductsPage;
import utilities.ReadConfig;

public class SDCartTest extends BaseTest {

    SDCartPage cartPage;
    SDLoginPage loginPage;
    SDProductsPage productsPage;
    ReadConfig rc = new ReadConfig();

    @BeforeMethod(inheritGroups = false)
    public void login() {
        cartPage = new SDCartPage();
        loginPage = new SDLoginPage();
        productsPage = new SDProductsPage();
        loginPage.openPage();
        loginPage.login(rc.getTestData("standard_user"), rc.getTestData("password"));
    }

    @Test(dataProviderClass = SDProductsTest.class, dataProvider = "itemInfo",description = "Checking quantity in cart")
    public void checkQuantityInCart(int itemNumber, String itemName, String itemPrice) {
        productsPage.addNthItemToCart(itemNumber);
        productsPage.openCart();
        cartPage.checkQuantityOfItem(1,1);
        cartPage.removeItemFromCart(1);
        cartPage.returnToShopping();
    }

    @Test(dataProviderClass = SDProductsTest.class, dataProvider = "itemInfo",description = "Checking name in cart")
    public void checkNameInCart(int itemNumber, String itemName, String itemPrice) {
        productsPage.addNthItemToCart(itemNumber);
        productsPage.openCart();
        cartPage.checkNameOfItem(1,itemName);
        cartPage.removeItemFromCart(1);
        cartPage.returnToShopping();
    }

    @Test(dataProviderClass = SDProductsTest.class, dataProvider = "itemInfo",description = "Checking price in cart")
    public void checkPriceInCart(int itemNumber, String itemName, String itemPrice) {
        productsPage.addNthItemToCart(itemNumber);
        productsPage.openCart();
        cartPage.checkPriceOfItem(1,itemPrice);
        cartPage.removeItemFromCart(1);
        cartPage.returnToShopping();
    }
}
