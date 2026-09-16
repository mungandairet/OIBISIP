public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Demo accounts
        bank.addAccount(new Account("A123", "1234", 1000.00));
        bank.addAccount(new Account("B456", "5678", 500.00));
        bank.addAccount(new Account("C789", "0000", 2000.00));

        System.out.println("Demo accounts:");
        System.out.println("  A123 / PIN 1234  (balance 1000)");
        System.out.println("  B456 / PIN 5678  (balance 500)");
        System.out.println("  C789 / PIN 0000  (balance 2000)");

        new ATM(bank).start();
    }
}