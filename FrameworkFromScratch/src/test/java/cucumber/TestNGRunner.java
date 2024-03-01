package cucumber;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//cucumber-testng
@CucumberOptions(
        features = "src/test/java/cucumber",
        glue = ".stepdefinations",
        monochrome = true,
        tags = "@ErrorValidation",
        plugin = {"html:target/cucumber.html"}
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
}
