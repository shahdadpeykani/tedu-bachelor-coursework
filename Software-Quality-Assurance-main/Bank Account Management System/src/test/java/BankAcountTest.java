import static org.junit.Assert.*;

import org.junit.Test;

public class BankAcountTest {


    @Test
    public void testDeposit() {
        BankAccount account = new BankAccount("12345", 100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.001);  
    }

    @Test
    public void testWithdrawSuccess() {
        BankAccount account = new BankAccount("12345", 100.0);
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance(), 0.001);  
    }

    @Test
    public void testWithdrawFail() {
        BankAccount account = new BankAccount("12345", 100.0);
        assertFalse(account.withdraw(200.0));  
        assertEquals(100.0, account.getBalance(), 0.001); 
    }

    @Test
    public void testInitialBalance() {
        BankAccount account = new BankAccount("67890", 200.0);
        assertEquals(200.0, account.getBalance(), 0.001);  
    }

}
