package repository;

import domain.Transactions;
import domain.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository {
    //Mapping accountNumber with list of transactions
    private final Map<String, List<Transactions>> txByAccount = new HashMap<>();

    public void add(Transactions transactions) {
        List<transactions>list = txByAccount.computeIfAbsent(transactions, getAccountNumber(),
                k -> new ArrayList<>());
        list.add(transactions);
    }
}
