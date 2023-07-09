package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.dtos.CreateBookRequest;
import com.example.LibraryManagementSystem.dtos.SearchBookRequest;
import com.example.LibraryManagementSystem.models.Book;
import com.example.LibraryManagementSystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void createBook(@RequestBody CreateBookRequest createBookRequest) {
        bookService.createOrUpdate(createBookRequest.to());
    }

    // method to get books on basis of different types of entity fields instead of creating n number of GET apis for each
    @GetMapping("/book")
    public List<Book> getBooks(@RequestBody @Valid SearchBookRequest searchBookRequest) throws Exception {
        // two methods
        return bookService.find(searchBookRequest.getSearchKey(),searchBookRequest.getSearchValue());
    }
}
