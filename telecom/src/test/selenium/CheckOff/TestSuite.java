import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuite {

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(SignUpRetailCustomerTest.class);
    suite.addTestSuite(LogInRetailCustomerTest.class);
    suite.addTestSuite(MRepCreateServiceTest.class);
    suite.addTestSuite(MRepCreatepackagTest.class);
    suite.addTestSuite(CRepAddServiceToUserTest.class);
    suite.addTestSuite(CRepCreateCommercialCustomerTest.class);
    suite.addTestSuite(CustomerAddPackageTest.class);
    suite.addTestSuite(OnlineBill.class);
    suite.addTestSuite(CustomerDeleteServiceTest.class);
    suite.addTestSuite(CustomerCancelServiceTest.class);
    suite.addTestSuite(MRepDeleteServiceTest.class);
    suite.addTestSuite(CustomerSetThresholdTest.class);
    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
