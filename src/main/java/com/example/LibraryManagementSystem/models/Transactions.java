package com.example.LibraryManagementSystem.models;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // for us , for indexing and querying

    private String txnId;  // for user, kind of dummy, unique and random

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;  // every enum needs enumerated type

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    private Double fine;

    @ManyToOne
    @JoinColumn
    private Book book;// one book can be a part of many transactions(issue and return etc)

    @ManyToOne
    @JoinColumn
    private Student student; // one student can be a part of many transactions

    @ManyToOne
    @JoinColumn
    private Admin admin; // one student can be a part of many transactions
}
