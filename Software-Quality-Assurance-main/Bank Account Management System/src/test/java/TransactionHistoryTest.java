import static org.junit.Assert.*;

import org.junit.Test;

public class TransactionHistoryTest {


    @Test
    public void testTransactionHistoryEmpty() {
        TransactionHistory history = new TransactionHistory();
        assertTrue(history.isTransactionEmpty()); 
    }

    @Test
    public void testAddTransaction() {
        TransactionHistory history = new TransactionHistory();
        history.addTransaction("Deposit $100");
        assertFalse(history.isTransactionEmpty());  
    }

    @Test
    public void testTransactionHistory() {
        TransactionHistory history = new TransactionHistory();
        history.addTransaction("Deposit $100");
        history.addTransaction("Withdraw $50");

        String[] expectedHistory = {"Deposit $100", "Withdraw $50"};
        assertArrayEquals(expectedHistory, history.getHistory());  
    }
    
    @Test
    public void testAccountNotNull() {
        BankAccount account = new BankAccount("67890", 200.0);
        assertNotNull(account); 
    }

}
