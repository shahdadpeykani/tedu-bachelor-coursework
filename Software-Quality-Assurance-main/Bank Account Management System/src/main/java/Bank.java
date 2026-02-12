public class Bank {
    private BankAccount[] accounts;
    private int accountCount;

    public Bank() {
        accounts = new BankAccount[10];
        accountCount = 0;
    }

    public void addAccount(BankAccount account) {
        if (accountCount < accounts.length) {
            accounts[accountCount++] = account;
        }
    }

    public BankAccount findAccount(String accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;  
    }

    public boolean transfer(String fromAccount, String toAccount, double amount) {
        BankAccount from = findAccount(fromAccount);
        BankAccount to = findAccount(toAccount);
        if (from != null && to != null && from.withdraw(amount)) {
            to.deposit(amount);
            return true;
        }
        return false;
    }

    public boolean accountExists(String accountNumber) {
        return findAccount(accountNumber) != null;
    }
}
