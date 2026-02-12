import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ BankTest.class, TransactionHistoryTest.class })
public class BankOpsTestSuite {

}
