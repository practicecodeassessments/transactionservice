package com.cgi.transactionservice.data.dto;

import com.cgi.transactionservice.util.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDto {
    private String accountNo;
    private double amount;
    private TransactionType transactionType;

}
