import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class Register {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testRegister() throws Exception {
    driver.get(baseUrl + "/register");
    driver.findElement(By.id("input01")).clear();
    driver.findElement(By.id("input01")).sendKeys("test@test.com");
    driver.findElement(By.id("input02")).clear();
    driver.findElement(By.id("input02")).sendKeys("Atest!1");
    driver.findElement(By.id("input03")).clear();
    driver.findElement(By.id("input03")).sendKeys("Atest!1");
    driver.findElement(By.xpath("//input[@value='Register']")).click();
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
