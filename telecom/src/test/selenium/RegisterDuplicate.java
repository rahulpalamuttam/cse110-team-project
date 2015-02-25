import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RegisterDuplicate {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
  }

  @Test
  public void testRegisterDuplicate() throws Exception {
    driver.get(baseUrl + "/register");
    driver.findElement(By.id("input01")).clear();
    driver.findElement(By.id("input01")).sendKeys("git110@ucsd.edu");
    driver.findElement(By.id("input02")).clear();
    driver.findElement(By.id("input02")).sendKeys("Hello");
    driver.findElement(By.id("input03")).clear();
    driver.findElement(By.id("input03")).sendKeys("Hello");
    driver.findElement(By.xpath("//input[@value='Register']")).click();
    try {
      assertTrue(isElementPresent(By.cssSelector("label.error")));
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

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}
