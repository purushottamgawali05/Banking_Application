package app;

import service.BankService;
import service.impl.BankServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome To Console Bank");

        //Input From User
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankServiceImpl();

        //To keep the user enter
        boolean running = true;
        while(running){
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
        switch (choice){
            case "1" -> openAccount(scanner, bankService);
            case "2" -> deposite(scanner);
            case "3" -> withdraw(scanner);
            case "4" -> transfer(scanner);
            case "5" -> statement(scanner);
            case "6" -> listAccounts(scanner);
            case "7" -> searchAccounts(scanner);
            case "0" -> running = false;
        }
        }
    }

    private static void openAccount(Scanner scanner, BankService bankService){
        System.out.println("Enter Customer Name: ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter Customer Email: ");
        String email = scanner.nextLine().trim();

        System.out.println("Enter Account Type(SAVING/CURRENT): ");
        String type = scanner.nextLine().trim();

        System.out.println("Enter Initial Deposite(Optional blank for 0)");
        String amountStr = scanner.nextLine().trim();
        //Converts string into double using Double Wrapper Class
        Double initial = Double.valueOf(amountStr);
        bankService.openAccount(name, email, type);
    }

    private static void deposite(Scanner scanner){


    }

    private static void withdraw(Scanner scanner){

    }

    private static void transfer(Scanner scanner){

    }

    private static void statement(Scanner scanner){

    }

    private static void listAccounts(Scanner scanner){

    }

    private static void searchAccounts(Scanner scanner){

    }




}
