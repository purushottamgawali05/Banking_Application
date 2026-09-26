package domain;

import java.time.LocalDateTime;

public class Transactions {

    private String id;
    private Type type;
    private String accountNumber;
    private Double amount;
    private LocalDateTime timestamp;
    private String note;

    public Transactions(String id, String accountNumber, Type type, Double amount, LocalDateTime timestamp, String note) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.note = note;
    }
}
