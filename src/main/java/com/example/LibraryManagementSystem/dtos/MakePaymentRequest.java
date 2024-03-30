package com.example.LibraryManagementSystem.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MakePaymentRequest {

    private Integer amount;

    private Integer studentId;

    private String transactionId;
}
