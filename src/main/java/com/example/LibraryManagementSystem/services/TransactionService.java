package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.dtos.InitiateTransactionRequest;
import com.example.LibraryManagementSystem.dtos.MakePaymentRequest;
import com.example.LibraryManagementSystem.models.*;
import com.example.LibraryManagementSystem.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {


    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    @Autowired
    AdminService adminService;


    @Value("${student.allowed.max-books}")
    Integer maxBooksAllowed;


    @Value("${student.allowed.duration}")
    Integer duration;

    public String InitiateTxn(InitiateTransactionRequest initiateTransactionRequest) throws Exception {

        // based on type of transaction we route to either issue or return
        return initiateTransactionRequest.getTransactionType() == TransactionType.ISSUE ?
                issuance(initiateTransactionRequest) : returnBook(initiateTransactionRequest);

    }

    private String issuance(InitiateTransactionRequest initiateTransactionRequest) throws Exception {
        Student student = studentService.find(initiateTransactionRequest.getStudentId());
        Admin admin = adminService.find(initiateTransactionRequest.getAdminId());
        List<Book> bookList = bookService.find("id",String.valueOf(initiateTransactionRequest.getBookId()));

        Book book = (bookList != null && bookList.size() > 0) ? bookList.get(0) : null;

        if(student == null || admin == null || book == null || book.getStudent() != null || student.getBookList().size() >= maxBooksAllowed)
        {
            throw new Exception("Invalid Request");
        }
        // now basic checks are done, now we are creating the transaction object of those properties, we could do this in DTOS as well

        Transactions transactions = null;
        try {
            transactions = Transactions.builder()
                    .txnId(UUID.randomUUID().toString())
                    .student(student)
                    .admin(admin)
                    .transactionType(initiateTransactionRequest.getTransactionType())
                    .transactionStatus(TransactionStatus.PENDING).
                    build();

            transactionRepository.save(transactions); // this object got saved in our database
            book.setStudent(student);

            bookService.createOrUpdate(book); // if book already there just update the student

            transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        }catch (Exception e){
            transactions.setTransactionStatus(TransactionStatus.FAILURE);
        }finally {
            transactionRepository.save(transactions);
        }

        return transactions.getTxnId();
    }

    private String returnBook(InitiateTransactionRequest initiateTransactionRequest) throws Exception {

        /**
         * Return
         * 1. If the book is valid or not and student is valid or not
         * 2. entry in the Txn table
         * 3. due date check and fine calculation
         * 4. if there is no fine, then de-allocate the book from student's name ==> book table
         */
        List<Book> bookList = bookService.find("id",String.valueOf(initiateTransactionRequest.getBookId()));
        Student student = studentService.find(initiateTransactionRequest.getStudentId());
        Admin admin = adminService.find(initiateTransactionRequest.getAdminId());

        Book book = bookList != null && bookList.size() > 0 ? bookList.get(0) : null;


        if(bookList == null || student == null || admin == null || book.getStudent().getId() != student.getId()){
            throw new Exception("Invalid Request");
        }

        // Getting the corresponding issuance txn
        Transactions issueTxn = transactionRepository.findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(student.getId(),book.getId(),TransactionType.ISSUE);

        if(issueTxn == null)
        {
            throw new Exception("InValid Request");
        }
        // Calculate Fine accordingly
        Integer fine = calculateFine(issueTxn.getCreatedOn());

        // logging the transactions
        Transactions transactions = null;
        try {
            transactions = Transactions.builder()
                    .txnId(UUID.randomUUID().toString())
                    .student(student)
                    .admin(admin)
                    .transactionType(initiateTransactionRequest.getTransactionType())
                    .transactionStatus(TransactionStatus.PENDING)
                    .fine(fine)
                    .build();
            transactionRepository.save(transactions);


            if (fine == 0) {
                book.setStudent(null);
                bookService.createOrUpdate(book);
                transactions.setTransactionStatus(TransactionStatus.SUCCESS);
            }
        }catch (Exception e){
                transactions.setTransactionStatus(TransactionStatus.FAILURE);
        }finally {
                transactionRepository.save(transactions);
        }
        return transactions.getTxnId();
    }

    // Here we calculate on the basis of the last transaction issued to this student
    private Integer calculateFine(Date issuanceTime){
        long issueTimeInMillis = issuanceTime.getTime(); // epoch time in millis , last issuance Date
        long currentTime = System.currentTimeMillis();

        // got the difference in MilliSeconds
        long diffInMillis =  currentTime-issueTimeInMillis;

        // convert the time into days
        long daysPassed = TimeUnit.DAYS.convert(diffInMillis,TimeUnit.MILLISECONDS);

        if(daysPassed > duration){
            long overDays = daysPassed-duration;
            if(overDays < 5)
            {
                return (int) overDays * 10;
            }
            else if(overDays < 21){
                return (int)overDays * 20;
            }else if(overDays > 21){
                return (int)overDays * 50;
            }
        }

        // if daysPassed are fine , not crossed fine limit
        return 0;
    }

    public void payfine(MakePaymentRequest makePaymentRequest) throws Exception {
        Transactions returntransactions = transactionRepository.findByTxnId(String.valueOf(makePaymentRequest.getTransactionId()));

        Book book = returntransactions.getBook();   // extract the book mapped to this transaction
        if(returntransactions.getFine() == makePaymentRequest.getAmount() && book.getStudent() != null && book.getStudent().getId() == makePaymentRequest.getStudentId()){
            returntransactions.setTransactionStatus(TransactionStatus.SUCCESS);
            book.setStudent(null);
            bookService.createOrUpdate(book);
            transactionRepository.save(returntransactions);
        }
        else{
            throw new Exception("Invalid Request!");
        }
    }

}
