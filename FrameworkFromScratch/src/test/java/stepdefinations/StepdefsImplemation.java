package stepdefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import org.pageObjects.*;
import org.testng.annotations.Test;
import testComponents.BaseTest;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;

public class StepdefsImplemation extends BaseTest {

    public LandingPage landingPage;
    public ProductCatlogPage productCatlogPage;
    public ConfirmationPage confirmationPage;
    @Given("I landed on ecommerce page")
    public void i_landed_on_ecommerce_page() throws IOException {
        landingPage=launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_john_smith_example_com_and_password_test_user(String username,String password) {
        productCatlogPage=landingPage.loginApplication(username,password);
    }
    @When("^I add product (.+) to cart$")
    public void i_add_product_zara_coat_to_cart(String productName) {
        List<WebElement> products=productCatlogPage.getProductList();
        //Add item to cart
        productCatlogPage.addProductToCart(productName);
    }
    @And("^Checkout (.+) and submit the order$")
    public void checkout_zara_coat_and_submit_the_order(String productName) {
        //click on cart button
        CartPage cartPage=productCatlogPage.goToCartPage();
        //Go to cart page
        Boolean match =cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        //click on checkout button
        CheckOutPage checkOutPage =cartPage.goToCheckout();
        checkOutPage.setSelectCountry("India");
        confirmationPage=checkOutPage.submitOrder();
    }
    @Then("{string} message is displayed on confirmation page")
    public void message_is_displayed_on_confirmation_page(String stringMsg) {
        String msg=confirmationPage.getConfirmationMsg();
        Assert.assertTrue(msg.equalsIgnoreCase(stringMsg));
        driver.close();
    }

    @Then("{string} message is displayed")
    public void message_is_dispalyed(String msg){
        Assert.assertEquals(msg,landingPage.getErrorMsg());
        driver.close();
    }
}
