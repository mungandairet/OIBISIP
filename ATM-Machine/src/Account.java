import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String userId;
    private String pin;
    private double balance;
    private final List<Transaction> transactions;

    public Account(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        if (initialBalance > 0) {
            transactions.add(new Transaction("OPENING", initialBalance));
        }
    }

    public boolean deposit(double amount) {
        if (amount <= 0) return false;
        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount));
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) return false;
        if (amount > balance) return false;
        balance -= amount;
        transactions.add(new Transaction("WITHDRAW", amount));
        return true;
    }

    boolean deduct(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    void credit(double amount) {
        balance += amount;
    }

    void log(String type, double amount) {
        transactions.add(new Transaction(type, amount));
    }

    public String getUserId() { return userId; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }

    public boolean checkPin(String input) {
        return pin.equals(input);
    }
}