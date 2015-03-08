package TurnIn3;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreatePackageTest {
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
  public void testCreatePackage() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Sign in")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("mrep");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("a");
    driver.findElement(By.xpath("//input[@value='Log in']")).click();
    driver.findElement(By.linkText("Packages")).click();
    driver.findElement(By.linkText("Add Package")).click();
    driver.findElement(By.name("packageName")).clear();
    driver.findElement(By.name("packageName")).sendKeys("New package");
    driver.findElement(By.name("description")).clear();
    driver.findElement(By.name("description")).sendKeys("new package discription");
    driver.findElement(By.name("price")).clear();
    driver.findElement(By.name("price")).sendKeys("200");
    driver.findElement(By.xpath("(//input[@name='add'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='add'])[3]")).click();
    driver.findElement(By.xpath("//input[@value='Submit']")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | css=table.table.table-striped.3.0 | ]]
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
