package service;

import domain.Account;

import java.util.List;

//De
public interface BankService {
    String openAccount(String name, String email, String accountType);
    //returns list of accounts
    List<Account> listAccounts();

    void deposite(String accountNumber, Double amount, String deposit);
}
