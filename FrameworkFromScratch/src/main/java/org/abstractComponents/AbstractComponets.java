package org.abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageObjects.CartPage;
import org.pageObjects.OrderPage;

import java.time.Duration;

public class AbstractComponets {

    WebDriver driver;
    WebDriverWait wait;

    CartPage cartPage;
    public AbstractComponets(WebDriver driver) {
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    @FindBy(css="[routerlink*='cart']")
    WebElement cartPageElement;

    @FindBy(css ="[routerlink*='myorders']" )
    WebElement orderHeader;
    public void waitForElementToAppear(By findBy){
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForElementToDisappear(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public CartPage goToCartPage(){
        cartPageElement.click();
        cartPage=new CartPage(driver);
        return cartPage;
    }

    public OrderPage goToOrderPage(){
        orderHeader.click();
        OrderPage orderPage=new OrderPage(driver);
        return orderPage;
    }
}
