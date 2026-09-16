import java.util.List;
import java.util.Scanner;

public class ATM {

    private final Bank bank;
    private final Scanner scanner;
    private Account currentAccount;

    public ATM(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== Welcome to the ATM ===");

        if (!login()) {
            System.out.println("Access denied. Too many failed attempts.");
            return;
        }

        System.out.println("\nWelcome, " + currentAccount.getUserId() + "!");
        menuLoop();
        System.out.println("Thank you for banking with us. Goodbye!");
        scanner.close();
    }

    private boolean login() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("User ID: ");
            String userId = scanner.next().trim();
            System.out.print("PIN: ");
            String pin = scanner.next().trim();

            Account account = bank.findAccount(userId);
            if (account != null && account.checkPin(pin)) {
                currentAccount = account;
                return true;
            }

            attempts++;
            System.out.println("Invalid credentials. Attempts remaining: " + (3 - attempts));
        }
        return false;
    }

    private void menuLoop() {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1: showHistory(); break;
                case 2: doWithdraw(); break;
                case 3: doDeposit(); break;
                case 4: doTransfer(); break;
                case 5: return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private void showHistory() {
        System.out.println("\n--- Transaction History ---");
        List<Transaction> list = currentAccount.getTransactions();
        if (list.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.printf("%-15s %10s   %s%n", "TYPE", "AMOUNT", "TIME");
        for (Transaction t : list) {
            System.out.println(t);
        }
        System.out.printf("%nCurrent Balance: %.2f%n", currentAccount.getBalance());
    }

    private void doWithdraw() {
        System.out.print("Amount to withdraw: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount.");
            scanner.next();
            return;
        }
        double amount = scanner.nextDouble();
        if (currentAccount.withdraw(amount)) {
            System.out.printf("Withdrew %.2f. New balance: %.2f%n",
                    amount, currentAccount.getBalance());
        } else {
            System.out.println("Insufficient Funds or invalid amount.");
        }
    }

    private void doDeposit() {
        System.out.print("Amount to deposit: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount.");
            scanner.next();
            return;
        }
        double amount = scanner.nextDouble();
        if (currentAccount.deposit(amount)) {
            System.out.printf("Deposited %.2f. New balance: %.2f%n",
                    amount, currentAccount.getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private void doTransfer() {
        System.out.print("Recipient User ID: ");
        String toId = scanner.next().trim();
        System.out.print("Amount to transfer: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount.");
            scanner.next();
            return;
        }
        double amount = scanner.nextDouble();

        if (bank.transfer(currentAccount.getUserId(), toId, amount)) {
            System.out.printf("Transferred %.2f to %s. New balance: %.2f%n",
                    amount, toId, currentAccount.getBalance());
        } else {
            System.out.println("Transfer failed. Check recipient ID, amount, or balance.");
        }
    }
}