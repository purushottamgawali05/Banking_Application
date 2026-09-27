package service.impl;

import domain.Account;
import repository.AccountRepository;
import service.BankService;

import java.util.UUID;

public class BankServiceImpl implements BankService {

    public final AccountRepository accountRepository = new AccountRepository();

    @Override
    public String openAccount(String name, String email, String accountType){

        //generate random customerId and accountNumber
        String customerId = UUID.randomUUID().toString();

        String accountNumber = getaccountNumber();


        Account account = new Account(accountNumber, accountType, (double) 0, customerId);
        accountRepository.save(account);
        return accountNumber;
    }

    private String getaccountNumber() {
        // String accountNumber = UUID.randomUUID().toString();
        int size = accountRepository.findAll().size() + 1;
        return String.format("AC%06d", size);
    }

}
