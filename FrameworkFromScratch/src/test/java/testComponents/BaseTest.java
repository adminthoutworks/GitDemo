package testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.pageObjects.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {
        Properties prop=new Properties();
       // File file=new File("GlobalData.properties").getAbsoluteFile();
        FileInputStream fis=new FileInputStream("/Users/juhikohale/Downloads/FrameworkFromScratch/src/test/java/resources/GlobalData.properties");
        prop.load(fis);
        String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
        //String browserName=prop.getProperty("browser");
        if(browserName.contains("chrome")) {
            ChromeOptions options=new ChromeOptions();
            if(browserName.contains("headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//help to run in full screen

        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //rerad json to string

        String jsonContent= FileUtils.readFileToString(new File(filePath));

        //String to Hashmap Jackson databind
        ObjectMapper mapper=new ObjectMapper();

        List<HashMap<String,String>> data =mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>() {
        });
        //map,map
        return data;
    }
    public String getScreenshots(String testCaseName,WebDriver driver) throws IOException {
        TakesScreenshot ts=(TakesScreenshot) driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        File file=new File(System.getProperty("user.dir")+"//src//testScreenshots"+ testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir") + "//src//testScreenshots" + testCaseName + ".png";
    }
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver=initializeDriver();
        landingPage=new LandingPage(driver);
        landingPage.goTo("https://rahulshettyacademy.com/client");
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
