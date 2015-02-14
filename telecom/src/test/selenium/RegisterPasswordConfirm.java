import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RegisterPasswordConfirm {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testRegisterPasswordConfirm() throws Exception {
        driver.get(baseUrl + "/register");
        driver.findElement(By.id("input01")).clear();
        driver.findElement(By.id("input01")).sendKeys("test@test.com");
        driver.findElement(By.id("input02")).clear();
        driver.findElement(By.id("input02")).sendKeys("Jabc");
        driver.findElement(By.id("input03")).clear();
        driver.findElement(By.id("input03")).sendKeys("Jabc");
        driver.findElement(By.xpath("//input[@value='Register']")).click();
        try {
            assertEquals("Register", driver.findElement(By.cssSelector("legend")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
