public class TransactionHistory {
    private String[] history;
    private int transactionCount;

    public TransactionHistory() {
        history = new String[100];  
        transactionCount = 0;
    }

    public void addTransaction(String transaction) {
        if (transactionCount < history.length) {
            history[transactionCount++] = transaction;
        }
    }

    public String[] getHistory() {
        String[] actualHistory = new String[transactionCount];
        for (int i = 0; i < transactionCount; i++) {
            actualHistory[i] = history[i];
        }
        return actualHistory;
    }

    public boolean isTransactionEmpty() {
        return transactionCount == 0;
    }
}
