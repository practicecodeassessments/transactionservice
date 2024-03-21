package com.cgi.transactionservice.service;


import com.cgi.transactionservice.data.dto.TransactionDto;
import com.cgi.transactionservice.data.entity.Transaction;
import com.cgi.transactionservice.repository.TransactionRepository;
import com.cgi.transactionservice.util.enums.TransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Transaction createTransaction(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(transactionDto.getAccountNo());
        transaction.setTransactionAmount(transactionDto.getAmount());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        return transactionRepository.save(transaction);
    }
    public List<Transaction> getTransactions(String accountNumber){
        return transactionRepository.findByAccountNumber(accountNumber);
    }
}
