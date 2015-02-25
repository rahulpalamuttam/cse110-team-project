import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class Services {
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
  public void testServices() throws Exception {
    driver.get(baseUrl + "/auth/login");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("git110@ucsd.edu");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("hello");
    driver.findElement(By.xpath("//input[@value='Log in']")).click();
    try {
//      assertEquals("Welcome git110@ucsd.edu !", driver.findElement(By.cssSelector("h2.sub-header")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.get(baseUrl + "/dashboard/services");
    driver.findElement(By.name("cancel")).click();
    driver.findElement(By.xpath("(//input[@name='cancel'])[2]")).click();
    driver.findElement(By.cssSelector("button.btn.btn-default")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | //div[2]/div/table.1.0 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | //div[2]/div/table.2.0 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | //div[2]/div/table.3.0 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | //div[2]/div/table.4.0 | ]]
    driver.findElement(By.xpath("(//input[@name='subscribePackage'])[3]")).click();
    driver.findElement(By.cssSelector("button.btn.btn-default")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | css=table.table.table-striped.1.0 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | //div[2]/div/table.1.0 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | //div[2]/div/table.2.0 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | //div[2]/div/table.3.0 | ]]
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
