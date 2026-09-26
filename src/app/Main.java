package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome To Console Bank");

        //Input From User
        Scanner sc = new Scanner(System.in);

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
        String Choice = sc.nextLine().trim();
        System.out.println("Choice:            " + Choice);

        switch (choice){
            case "0" -> running = false;
        }
        }
    }
}
