import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class TopMenu {
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
  public void testTopMenu() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Logo")).click();
    driver.findElement(By.linkText("About")).click();
    driver.findElement(By.linkText("Services")).click();
    driver.findElement(By.linkText("Register")).click();
    driver.findElement(By.linkText("Sign in")).click();
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
