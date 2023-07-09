package com.example.LibraryManagementSystem.controllers;


import com.example.LibraryManagementSystem.dtos.InitiateTransactionRequest;
import com.example.LibraryManagementSystem.dtos.MakePaymentRequest;
import com.example.LibraryManagementSystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @PostMapping("/transaction")
    public String initiateTxn(@RequestBody @Valid InitiateTransactionRequest initiateTransactionRequest) throws Exception{

        return transactionService.InitiateTxn(initiateTransactionRequest);
    }
    @PostMapping("/transaction/payment")
    public void makePayment(@RequestBody @Valid MakePaymentRequest makePaymentRequest) throws Exception {
        transactionService.payfine(makePaymentRequest);
    }
}
