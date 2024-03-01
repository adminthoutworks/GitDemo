package org.pageObjects;

import org.abstractComponents.AbstractComponets;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponets {

    WebDriver driver;
    ProductCatlogPage productCatlogPage;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
     }
     @FindBy(id="userEmail")
     WebElement username;

    @FindBy(id="userPassword")
    WebElement password;

    @FindBy(id="login")
    WebElement loginBtn;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMsg;
    public ProductCatlogPage loginApplication(String email, String pass){
        username.sendKeys(email);
        password.sendKeys(pass);
        loginBtn.click();
        productCatlogPage=new ProductCatlogPage(driver);
        return productCatlogPage;
    }

    public void goTo(String url){
        driver.get(url);
    }

    public String getErrorMsg(){
        waitForWebElementToAppear(errorMsg);
       return errorMsg.getText();
    }
}
