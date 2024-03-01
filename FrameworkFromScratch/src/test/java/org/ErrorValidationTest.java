package org;

import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import org.pageObjects.CartPage;
import org.pageObjects.ProductCatlogPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testComponents.BaseTest;
import testComponents.Retry;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"errorHandling"},retryAnalyzer = Retry.class)
    public void LoginErrorValidationTest(){
        ProductCatlogPage productCatlogPage=landingPage.loginApplication("john.smith@example.com","testUser123");
        Assert.assertEquals("Incorrect email  password.",landingPage.getErrorMsg());
    }

    @Test
    public void productErrorValidation() throws IOException,InterruptedException {

        String productName="ZARA COAT 3";
        //user-john.smith@example.com
        //password- testUser@123
        ProductCatlogPage productCatlogPage=landingPage.loginApplication("john.smith@example.com","testUser@123");
        //get product list
        List<WebElement> products=productCatlogPage.getProductList();
        //Add item to cart
        productCatlogPage.addProductToCart(productName);
        //click on cart button
        CartPage cartPage=productCatlogPage.goToCartPage();
        //Go to cart page
        Boolean match =cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }

}
