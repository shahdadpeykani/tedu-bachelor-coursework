import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BankAccountParameterizedTest {


    private double initialBalance;
    private double withdrawalAmount;
    private boolean expectedOutcome;  
    private double expectedBalance;    

    
    public BankAccountParameterizedTest(double initialBalance, double withdrawalAmount, boolean expectedOutcome, double expectedBalance) {
        this.initialBalance = initialBalance;
        this.withdrawalAmount = withdrawalAmount;
        this.expectedOutcome = expectedOutcome;
        this.expectedBalance = expectedBalance;
    }

    
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
            {200.0, 50.0, true, 150.0},    
            {100.0, 150.0, false, 100.0},   
            {0.0, 10.0, false, 0.0},        
            {300.0, 100.0, true, 200.0},    
            {500.0, 500.0, true, 0.0}       
        };
    }

    @Test
    public void testWithdraw() {
        BankAccount account = new BankAccount("12345", initialBalance);
        boolean result = account.withdraw(withdrawalAmount);
        assertEquals(expectedOutcome, result);  
        assertEquals(expectedBalance, account.getBalance(), 0.001);  
    }

}
