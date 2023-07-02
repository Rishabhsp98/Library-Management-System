package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.dtos.CreateBookRequest;
import com.example.LibraryManagementSystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    BookService bookService;
    @PostMapping("/book")
    public void createBook(@RequestBody CreateBookRequest createBookRequest)
    {
        bookService.create(createBookRequest.to());
    }
}
