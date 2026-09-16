import java.util.HashMap;
import java.util.Map;

public class Bank {

    private final Map<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getUserId(), account);
    }

    public Account findAccount(String userId) {
        return accounts.get(userId);
    }

    public boolean transfer(String fromId, String toId, double amount) {
        Account from = accounts.get(fromId);
        Account to = accounts.get(toId);

        if (from == null || to == null) return false;
        if (fromId.equals(toId)) return false;
        if (amount <= 0) return false;
        if (amount > from.getBalance()) return false;

        from.deduct(amount);
        from.log("TRANSFER_OUT", amount);

        to.credit(amount);
        to.log("TRANSFER_IN", amount);

        return true;
    }
}