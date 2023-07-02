package com.example.LibraryManagementSystem.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // giving the Database the power not to hibernate
    private Integer id;

    private String name;

    @Enumerated(value =  EnumType.STRING)        // by default its ordinal(means numbered), we are using string based for better readability
    private Genre genre; //   Either Store as String or ENUM

    @CreationTimestamp
    private Date createOn;

    @UpdateTimestamp
    private Date updateOn;

    @ManyToOne
    @JoinColumn
    private Author my_author;

    @ManyToOne
    @JoinColumn
    private Student student;

    @OneToMany(mappedBy = "book")
    private List<Transactions> transactions;
}


