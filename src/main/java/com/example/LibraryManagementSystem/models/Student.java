package com.example.LibraryManagementSystem.models;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    private Integer age;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "student")
    private List<Book> bookList;  // back reference

    @OneToMany(mappedBy = "student")
    private List<Transactions> transactions;  // back reference
}
