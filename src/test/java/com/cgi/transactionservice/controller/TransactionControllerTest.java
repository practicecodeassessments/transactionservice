package com.cgi.transactionservice.controller;

import com.cgi.transactionservice.data.dto.TransactionDto;
import com.cgi.transactionservice.data.entity.Transaction;
import com.cgi.transactionservice.service.TransactionService;
import com.cgi.transactionservice.util.enums.TransactionStatus;
import com.cgi.transactionservice.util.enums.TransactionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.HttpStatusCode;


@SpringBootTest
public class TransactionControllerTest
{
    @InjectMocks
    private TransactionController transactionController;
    @Mock
    private TransactionService transactionService;

    @Test
    public void createTransactionSuccess() throws Exception
    {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionType( TransactionType.valueOf( "CREDIT" ) );
        transactionDto.setAmount( 1000 );
        transactionDto.setAccountNo( "123456789");
        Transaction transaction = new Transaction();
        transaction.setTransactionId( "1234" );
        transaction.setTransactionStatus( TransactionStatus.valueOf( "SUCCESSFUL" ) );
        transaction.setTransactionAmount( 1000 );
        transaction.setAccountNumber("123456789" );
        when(transactionService.createTransaction( any() )).thenReturn(transaction  );
        ResponseEntity actualResponseEntity = transactionController.createTransaction(transactionDto);
        assertEquals(HttpStatusCode.valueOf(200), actualResponseEntity.getStatusCode());
        Transaction transactionResponse = (Transaction) actualResponseEntity.getBody();
        assert transactionResponse!= null;
        assertEquals("123456789", transactionResponse.getAccountNumber());
    }

    @Test
    public void retrieveTransactionSuccess() throws Exception
    {
        String accountNumber ="123456789";
        Transaction transaction = new Transaction();
        transaction.setTransactionId( "1234" );
        transaction.setTransactionStatus( TransactionStatus.valueOf( "SUCCESSFUL" ) );
        transaction.setTransactionAmount( 1000 );
        transaction.setAccountNumber("123456789" );
        List<Transaction>  transactionList = new ArrayList<>();
        transactionList.add(transaction);
        when(transactionService.getTransactions( any() )).thenReturn(transactionList  );
        ResponseEntity actualResponseEntity = transactionController.getTransactions(accountNumber);
        assertEquals(HttpStatusCode.valueOf(200), actualResponseEntity.getStatusCode());
        List<Transaction>  transactionListResponse= (List<Transaction>) actualResponseEntity.getBody();
        assert transactionListResponse!= null;
        assertEquals("123456789", transactionListResponse.get(0).getAccountNumber());

    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
