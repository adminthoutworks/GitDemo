package org;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class standaloneTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String productName="ZARA COAT 3";
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));

        //user-john.smith@example.com
        //password- testUser@123

        driver.findElement(By.id("userEmail")).sendKeys("john.smith@example.com");
        driver.findElement(By.id("userPassword")).sendKeys("testUser@123");
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod=products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        //add to cart button
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        //wait till taod msg popup dissappear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-animating")));

        //click on cart button
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartSelection=driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match=cartSelection.stream().anyMatch(cartSelect -> cartSelect.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        //click on checkout button
        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions a= new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();

        driver.findElement(By.cssSelector(".action__submit")).click();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();

    }
}
