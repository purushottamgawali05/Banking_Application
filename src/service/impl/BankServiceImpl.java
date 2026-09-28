package service.impl;

import domain.Account;
import domain.Transaction;
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

        String accountNumber = getAccountNumber();
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
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), account.getAccountNumber(), Type.DEPOSIT
                , amount, LocalDateTime.now(), note);
        transactionRepository.add(transaction);
    }

    @Override
    public void withdraw(String accountNumber, Double amount, String note) {
        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account Not Found: " + accountNumber));
        if(account.getBalance().compareTo(amount) < 0){
            throw new RuntimeException("Insufficient Balance");
        }
        account.setBalance(account.getBalance() - amount);
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), account.getAccountNumber(), Type.WITHDRAW
                , amount, LocalDateTime.now(), note);
        transactionRepository.add(transaction);
    }

    @Override
    public void transfer(String fromAcc, String toAcc, Double amount, String note) {
        //if sender's and receiver's account are same
        if(fromAcc.equals(toAcc)){
            throw new RuntimeException("Cannot transfer to your own account");
        }
        //finding the from account
        Account from = accountRepository.findByNumber(fromAcc)
        //if the fromAcc Doesn't exist
                .orElseThrow(() -> new RuntimeException("Account Not Found: "));
        Account to = accountRepository.findByNumber(toAcc)
        //if the fromAcc Doesn't exist
                .orElseThrow(() -> new RuntimeException("Account Not Found: "));
        //Check the account have that much money to transfer
        if(from.getBalance().compareTo(amount) < 0){
            throw new RuntimeException("Insufficient Balance");
        }

        //Withdraw from fromAcc
        from.setBalance(from.getBalance() - amount);
        //Deposit to toAcc
        to.setBalance(to.getBalance() + amount);

        //transfer from my account to another
        transactionRepository.add(new Transaction( UUID.randomUUID().toString(), from.getAccountNumber(),
                Type.TRANSFER_OUT, amount, LocalDateTime.now(), note));

        //transfer from another's to my account
        transactionRepository.add(new Transaction( UUID.randomUUID().toString(), to.getAccountNumber(),
                Type.TRANSFER_IN, amount, LocalDateTime.now(), note));


    private String getAccountNumber() {
        // String accountNumber = UUID.randomUUID().toString();
        int size = accountRepository.findAll().size() + 1;
        return String.format("AC%06d", size);
    }

}
