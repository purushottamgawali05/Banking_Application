package service;

import domain.Account;
import domain.Transaction;

import java.util.List;

//De
public interface BankService {

    List<Transaction> getStatement(String account);

    String openAccount(String name, String email, String accountType);
    //returns list of accounts
    List<Account> listAccounts();

    void deposit(String accountNumber, Double amount, String note);

    void withdraw(String accountNumber, Double amount, String withdrawal);

    void transfer(String fromAcc, String toAcc, Double amount, String transfer);
}
