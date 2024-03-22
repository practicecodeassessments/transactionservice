package com.cgi.transactionservice.controller;

import com.cgi.transactionservice.data.dto.TransactionDto;
import com.cgi.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {
    private final TransactionService transactionservice;

    @PostMapping
    public ResponseEntity createTransaction(@RequestBody TransactionDto transactionDto){
        log.info("Creating transaction on account {}",transactionDto.getAccountNo());
        return ResponseEntity.ok(transactionservice.createTransaction(transactionDto));
    }

    @GetMapping
    public ResponseEntity getTransactions(@RequestParam(name = "accountNumber") String accountNumber){
        return ResponseEntity.ok(transactionservice.getTransactions(accountNumber));
    }
}
