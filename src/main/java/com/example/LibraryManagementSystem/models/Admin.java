package com.example.LibraryManagementSystem.models;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @CreationTimestamp
    private Date CreatedOn;


    @OneToMany(mappedBy = "admin")
    private List<Transactions> transactionsList;

}
