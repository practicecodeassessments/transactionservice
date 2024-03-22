package com.cgi.transactionservice.data.entity;


import com.cgi.transactionservice.util.enums.TransactionStatus;
import com.cgi.transactionservice.util.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp transactionDate;

    private String accountNumber;

    private double transactionAmount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(nullable = false, unique = true)
    private String transactionId;

    @PrePersist
    public void prePersist() {
        transactionDate = Timestamp.from(Calendar.getInstance().toInstant());
        transactionId = String.valueOf(new Date().getTime());
    }
}
