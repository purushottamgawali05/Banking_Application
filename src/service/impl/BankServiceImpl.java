package service.impl;

import domain.Account;
import repository.AccountRepository;
import service.BankService;

import java.util.List;
import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {

    public final AccountRepository accountRepository = new AccountRepository();

    @Override
    public String openAccount(String name, String email, String accountType){

        //generate random customerId and accountNumber
        String customerId = UUID.randomUUID().toString();

        String accountNumber = getaccountNumber();
        Account account = new Account(accountNumber, customerId, (double) 0, accountType);
        accountRepository.save(account);
        return accountNumber;
    }

    @Override
    public List<Account> listAccounts() {
        return accountRepository.findAll().stream()
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }

    @Override
    public void deposite(String accountNumber, Double amount, String deposit) {
        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account Not Found: " + accountNumber));
        account.setBalance(Double.valueOf(account.getBalance() + deposit));
    }

    private String getaccountNumber() {
        // String accountNumber = UUID.randomUUID().toString();
        int size = accountRepository.findAll().size() + 1;
        return String.format("AC%06d", size);
    }

}
