package com.example.LibraryManagementSystem.dtos;


import com.example.LibraryManagementSystem.models.TransactionType;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InitiateTransactionRequest {

    @NotNull
    private Integer bookId;

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer adminId;

    @NotNull
    private TransactionType transactionType;
}
