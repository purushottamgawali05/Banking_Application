# Banking Application

A console-based banking system built using Java and object-oriented programming concepts. The application allows users to create accounts, deposit and withdraw money, transfer funds between accounts, view transaction history, and search accounts by customer name.

## Features

- Open a new bank account
- Deposit funds into an account
- Withdraw funds with balance validation
- Transfer money between accounts
- View account statement / transaction history
- List all bank accounts
- Search accounts by customer name
- Input validation and custom exceptions for invalid operations

## Project Structure

```text
Banking_Application/
├── src/
│   ├── app/
│   │   └── Main.java
│   ├── domain/
│   │   ├── Account.java
│   │   ├── Customer.java
│   │   ├── Transaction.java
│   │   └── Type.java
│   ├── exceptions/
│   │   ├── AccountNotFoundException.java
│   │   ├── InsufficientFundException.java
│   │   └── ValidationException.java
│   ├── repository/
│   │   ├── AccountRepository.java
│   │   ├── CustomerRepository.java
│   │   └── TransactionRepository.java
│   ├── service/
│   │   ├── BankService.java
│   │   └── impl/
│   │       └── BankServiceImpl.java
│   └── util/
│       └── Validation.java
└── README.md
```

## Core Concepts Used

- Encapsulation through domain classes
- Inheritance / interface-based service design
- Repository pattern for data storage
- Validation layer for business rules
- Exception handling for invalid operations
- Console-based user interaction using Java Scanner

## How to Run

From the project root, compile the Java source files and run the main class.

After launching, the application will display a menu with options such as:

1. Open Account
2. Deposit
3. Withdraw
4. Transfer
5. Account Statement
6. List Accounts
7. Search Accounts by Customer Name
0. Exit

## Notes

- Account types accepted are SAVING and CURRENT.
- Email validation checks for a valid email format containing @.
- Withdrawals and transfers cannot exceed the available balance.
- Transaction data is stored in memory during runtime.

## Future Enhancements

- Persist data to a file or database
- Add login and authentication
- Add admin panel or user roles
- Add sorting and filtering for transactions
