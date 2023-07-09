package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.models.TransactionType;
import com.example.LibraryManagementSystem.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transactions,Integer> {

    //  @Query("select * from Transactions where student_id = ?1 and book_id = ?2 and transactionType = ?3 order by id desc limit 1")
    Transactions findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(Integer studentId, Integer bookId, TransactionType transactionType);


    Transactions findByTxnId(String txnId);
}
