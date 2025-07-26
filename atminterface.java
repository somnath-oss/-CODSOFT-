import java.util.Scanner;
import java.util.LinkedList;

class BankAccount {
    private double balance;
    private String lastTransaction = "No transactions yet.";
    private LinkedList<String> transactionHistory = new LinkedList<>();

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            lastTransaction = "Deposited " + amount;
            addToHistory(lastTransaction);
            System.out.println(" " + lastTransaction);
        } else {
            System.out.println(" Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println(" Insufficient balance.");
        } else if (amount <= 0) {
            System.out.println(" Invalid withdrawal amount.");
        } else {
            balance -= amount;
            lastTransaction = "Withdrawn " + amount;
            addToHistory(lastTransaction);
            System.out.println(" " + lastTransaction);
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getLastTransaction() {
        return lastTransaction;
    }

    public void showHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println(" No transaction history available.");
        } else {
            System.out.println(" Last 5 Transactions:");
            for (String txn : transactionHistory) {
                System.out.println(" - " + txn);
            }
        }
    }

    private void addToHistory(String transaction) {
        if (transactionHistory.size() == 5) {
            transactionHistory.removeFirst(); // remove oldest
        }
        transactionHistory.add(transaction);
    }
}

public class atminterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(10000); // starting balance
        int correctPin = 1234;
        int attempts = 0;
        boolean authenticated = false;

        System.out.println(" Welcome to Secure Java ATM");

        // Step 1: PIN Authentication
        while (attempts < 3) {
            System.out.print("Enter your 4-digit PIN: ");
            int enteredPin = scanner.nextInt();

            if (enteredPin == correctPin) {
                authenticated = true;
                break;
            } else {
                attempts++;
                System.out.println(" Incorrect PIN. Attempts left: " + (3 - attempts));
            }
        }

        if (!authenticated) {
            System.out.println(" Too many failed attempts. Exiting...");
            scanner.close();
            return;
        }

        //  ATM Menu
        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Last Transaction");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");

            System.out.print("Enter choice (1-6): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(" Current Balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double deposit = scanner.nextDouble();
                    account.deposit(deposit);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdraw = scanner.nextDouble();
                    account.withdraw(withdraw);
                    break;
                case 4:
                    System.out.println(" Last Transaction: " + account.getLastTransaction());
                    break;
                case 5:
                    account.showHistory();
                    break;
                case 6:
                    System.out.println(" Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println(" Invalid choice. Please enter 1-6.");
            }
        }
    }
}
