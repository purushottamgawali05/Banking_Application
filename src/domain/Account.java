package domain;

public class Account {
    //Class data-members
    private String accountNumber;
    private String customerId;
    private Double balance;
    private String accountType;

    //Constructor to initialize class data members
    public Account(String accountNumber, String customerId, Double balance, String accountType){
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = balance;
        this.accountType = accountType;
    }
}
