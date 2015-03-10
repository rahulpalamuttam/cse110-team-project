package CheckOff;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SignUpRetailCustomerTest {
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
  public void testSignUpRetailCustomer() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Register")).click();
    driver.findElement(By.id("input01")).clear();
    driver.findElement(By.id("input01")).sendKeys("a@a.com");
    driver.findElement(By.id("input02")).clear();
    driver.findElement(By.id("input02")).sendKeys("a");
    driver.findElement(By.id("input03")).clear();
    driver.findElement(By.id("input03")).sendKeys("a");
    driver.findElement(By.id("input04")).click();
    driver.findElement(By.xpath("//input[@value='Register']")).click();
    driver.findElement(By.linkText("Dashboard")).click();
    try {
      assertEquals("Welcome a@a.com !", driver.findElement(By.cssSelector("h2.sub-header")).getText());
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

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
