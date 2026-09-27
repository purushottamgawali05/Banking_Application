package service;

import domain.Account;

import java.util.ArrayList;

//De
public interface BankService {
    String openAccount(String name, String email, String accountType);

    ArrayList<Account> listAccounts();
}
