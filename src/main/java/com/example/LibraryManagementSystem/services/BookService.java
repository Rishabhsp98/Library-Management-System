package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.models.Author;
import com.example.LibraryManagementSystem.models.Book;
import com.example.LibraryManagementSystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {


    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository bookRepository;
    public void create(Book book) {

        Author bookauthor = book.getMy_author(); // here this author doesn't have primary key , so in next step
        Author savedauthor = authorService.getOrCreate(bookauthor); // here we are retrieving the complete author details having primary key

        // map the author to the book
        book.setMy_author(savedauthor);

        // now after mapping the foreign key above ,safely we save the book
        bookRepository.save(book);
    }
}
