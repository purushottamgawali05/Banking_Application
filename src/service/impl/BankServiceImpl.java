package service.impl;

import domain.Account;
import domain.Transactions;
import domain.Type;
import repository.AccountRepository;
import repository.TransactionRepository;
import service.BankService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {

    public final AccountRepository accountRepository = new AccountRepository();
    public final TransactionRepository transactionRepository = new TransactionRepository();

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
    public void deposit(String accountNumber, Double amount, String note) {
        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account Not Found: " + accountNumber));
        account.setBalance(account.getBalance() + amount);
        Transactions transactions = new Transactions(account.getAccountNumber(), amount,
                UUID.randomUUID().toString(), note, LocalDateTime.now(), Type.DEPOSIT);
        TransactionRepository.add(transactions);
    }

    private String getaccountNumber() {
        // String accountNumber = UUID.randomUUID().toString();
        int size = accountRepository.findAll().size() + 1;
        return String.format("AC%06d", size);
    }

}
