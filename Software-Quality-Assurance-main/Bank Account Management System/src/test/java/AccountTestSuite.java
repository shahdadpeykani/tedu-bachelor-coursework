import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ BankAccountParameterizedTest.class, BankAcountTest.class })
public class AccountTestSuite {

}
