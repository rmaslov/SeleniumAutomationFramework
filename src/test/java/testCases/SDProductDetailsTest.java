package testCases;

import base.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.SDLoginPage;
import pageObjects.SDProductDetailsPage;
import pageObjects.SDProductsPage;
import utilities.ReadConfig;

public class SDProductDetailsTest extends BaseTest {

    SDProductsPage productsPage = new SDProductsPage();
    SDProductDetailsPage productDetailsPage = new SDProductDetailsPage();
    SDLoginPage loginPage = new SDLoginPage();
    ReadConfig rc = new ReadConfig();


    @BeforeMethod
    public void login() {
        loginPage.openPage();
        loginPage.login(rc.getTestData("standard_user"), rc.getTestData("password"));
    }

    @Test(dataProviderClass = SDProductsTest.class, dataProvider = "itemInfo", description = "Adding items to Cart", priority = 1)
    public void addAndRemoveItem(int itemNumber, String itemName, String itemPrice) {
        productsPage.openNthItem(itemNumber);
        productDetailsPage.addItemToCart();
        productDetailsPage.checkNumberOfItemsInCart(1);
        productDetailsPage.removeItemFromCart();
        productDetailsPage.checkNumberOfItemsInCart(0);
        productDetailsPage.navigateBackToProducts();
    }

    @Test(dataProviderClass = SDProductsTest.class, dataProvider = "itemInfo", description = "Checking item name", priority = 3)
    public void checkItemName(int itemNumber, String itemName, String itemPrice) {
        productsPage.openNthItem(itemNumber);
        assert productDetailsPage.getItemName().equals(itemName);
        productDetailsPage.navigateBackToProducts();
    }

    @Test(dataProviderClass = SDProductsTest.class, dataProvider = "itemInfo", description = "Checking item price", priority = 3)
    public void checkItemPrice(int itemNumber, String itemName, String itemPrice) {
        productsPage.openNthItem(itemNumber);
        assert productDetailsPage.getItemPrice().equals(itemPrice);
        productDetailsPage.navigateBackToProducts();
    }

}
