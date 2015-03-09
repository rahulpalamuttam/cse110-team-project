package TurnIn3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CreateServiceTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCreateService() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Sign in")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("mrep");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("a");
    driver.findElement(By.xpath("//input[@value='Log in']")).click();
    driver.findElement(By.linkText("Service")).click();
    driver.findElement(By.linkText("Add Service")).click();
    driver.findElement(By.name("serviceName")).clear();
    driver.findElement(By.name("serviceName")).sendKeys("New Service");
    driver.findElement(By.name("serviceDescription")).clear();
    driver.findElement(By.name("serviceDescription")).sendKeys("New Service Description");
    driver.findElement(By.name("price")).clear();
    driver.findElement(By.name("price")).sendKeys("100");
    driver.findElement(By.xpath("//input[@value='Submit']")).click();
    try {
      assertEquals("New Service", driver.findElement(By.xpath("//tr[5]/td")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("Sign out")).click();
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
