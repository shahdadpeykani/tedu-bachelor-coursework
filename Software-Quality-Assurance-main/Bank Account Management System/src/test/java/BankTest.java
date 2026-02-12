import static org.junit.Assert.*;

import org.junit.Test;

public class BankTest {


    @Test
    public void testAddAccount() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount("12345", 100.0);
        bank.addAccount(account);
        assertTrue(bank.accountExists("12345"));  
    }

    @Test
    public void testAccountExists() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount("12345", 100.0);
        bank.addAccount(account);

        assertTrue(bank.accountExists("12345")); 
        assertFalse(bank.accountExists("67890"));  
    }

    @Test
    public void testTransferSuccess() {
        Bank bank = new Bank();
        BankAccount account1 = new BankAccount("12345", 200.0);
        BankAccount account2 = new BankAccount("54321", 100.0);
        bank.addAccount(account1);
        bank.addAccount(account2);

        assertTrue(bank.transfer("12345", "54321", 50.0)); 
        assertEquals(150.0, account1.getBalance(), 0.001);  
        assertEquals(150.0, account2.getBalance(), 0.001);  
    }

    @Test
    public void testTransferFail() {
        Bank bank = new Bank();
        BankAccount account1 = new BankAccount("12345", 100.0);
        BankAccount account2 = new BankAccount("54321", 100.0);
        bank.addAccount(account1);
        bank.addAccount(account2);

        assertFalse(bank.transfer("12345", "54321", 150.0));  
        assertEquals(100.0, account1.getBalance(), 0.001); 
        assertEquals(100.0, account2.getBalance(), 0.001); 
    }

}
