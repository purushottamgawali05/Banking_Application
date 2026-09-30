package app;

import java.util.Locale;
import java.util.Scanner;
import service.BankService;
import service.impl.BankServiceImpl;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome To Console Bank");

        //Input From User
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankServiceImpl();

        //To keep the user enter
        boolean running = true;
        while (running) {
            //Pre-formatted String
            System.out.println("""
                1. Open Account
                2. Deposit
                3. Withdraw
                4. Transfer
                5. Account Statement
                6. List Accounts
                7. Search Accounts by Customer Name
                0. Exit
                """);
            System.out.print("Enter Your Choice: ");
            String choice = scanner.nextLine().trim();
//        System.out.println("Choice:            " + Choice);

            //calling methods on the basis of user input
            switch (choice) {
                case "1" ->
                    openAccount(scanner, bankService);
                case "2" ->
                    deposit(scanner, bankService);
                case "3" ->
                    withdraw(scanner, bankService);
                case "4" ->
                    transfer(scanner, bankService);
                case "5" ->
                    statement(scanner, bankService);
                case "6" ->
                    listAccounts(bankService);
                case "7" ->
                    searchAccounts(scanner, bankService);
                case "0" ->
                    running = false;
                default ->
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void openAccount(Scanner scanner, BankService bankService) {
        System.out.println("Enter Customer Name: ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter Customer Email: ");
        String email = scanner.nextLine().trim();

        System.out.println("Enter Account Type(SAVING/CURRENT): ");
        String type = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

        System.out.println("Enter Initial Deposit(Optional blank for 0)");
        String amountStr = scanner.nextLine().trim();
        //Converts string into double using Double Wrapper Class
        double initial = amountStr.isEmpty() ? 0.0 : readDouble(scanner, "Initial Deposit", amountStr);
        String accountNumber = bankService.openAccount(name, email, type);
        if (initial > 0) {
            bankService.deposit(accountNumber, initial, "Initial Deposit");
        }
        System.out.println("Account Opened: " + accountNumber);
    }

    private static void deposit(Scanner scanner, BankService bankService) {
        System.out.println("Account Number: ");
        String accountNumber = scanner.nextLine().trim();

        System.out.println("Amount: ");
        Double amount = readDouble(scanner, "Deposit amount", scanner.nextLine().trim());
        bankService.deposit(accountNumber, amount, "note");
        System.out.println("Deposited");

    }

    private static void withdraw(Scanner scanner, BankService bankService) {
        System.out.println("Account Number: ");
        String accountNumber = scanner.nextLine().trim();
        System.out.println("Amount: ");
        Double amount = readDouble(scanner, "Withdrawal amount", scanner.nextLine().trim());
        bankService.withdraw(accountNumber, amount, "Withdrawal");
        System.out.println("Withdrawn");

    }

    private static void transfer(Scanner scanner, BankService bankService) {
        System.out.println("From Account");
        String fromAcc = scanner.nextLine().trim();
        System.out.println("To Account");
        String toAcc = scanner.nextLine().trim();
        System.out.println("Amount: ");
        Double amount = readDouble(scanner, "Transfer amount", scanner.nextLine().trim());
        bankService.transfer(fromAcc, toAcc, amount, "Transfer");
        System.out.println("Transfer");
    }

    private static double readDouble(Scanner scanner, String label, String rawValue) {
        while (true) {
            try {
                return Double.parseDouble(rawValue);
            } catch (NumberFormatException e) {
                System.out.println(label + " must be a valid number. Please try again:");
                rawValue = scanner.nextLine().trim();
            }
        }
    }

    private static void statement(Scanner scanner, BankService bankService) {
        System.out.println("Account Number: ");
        String account = scanner.nextLine().trim();
        bankService.getStatement(account).forEach(t -> {
            System.out.println(t.getTimestamp() + " | " + t.getType() + " | " + t.getAmount() + " | " + t.getNote());
        });
    }

    private static void listAccounts(BankService bankService) {
        bankService.listAccounts().forEach(t -> {
            System.out.println(t.getAccountNumber() + " | " + t.getAccountType() + " | " + t.getBalance());
        });
    }

    private static void searchAccounts(Scanner scanner, BankService bankService) {
        System.out.println("Customer Name Contains: ");
        String c_name = scanner.nextLine().trim();
        bankService.searchAccountByCustomerName(c_name).forEach(account -> System.out.println(account.getAccountNumber() + " | "
                + account.getAccountType() + " | " + account.getBalance())
        );
    }
}
