package repository;

import domain.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountRepository {

    //Saves data in the form of accountNumber and account object
    private final Map<String, Account> accountByNumber = new HashMap<>();

    public void save(Account account){

        accountByNumber.put(account.getAccountNumber(), account);
    }

        public ArrayList<Account> findAll(){
            return new ArrayList<>(accountByNumber.values());
        }

}
