package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.models.Author;
import com.example.LibraryManagementSystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public Author getOrCreate(Author author){

        Author authorRetrived = authorRepository.findByEmail(author.getEmail());
        if(authorRetrived == null)
        {
            authorRetrived = authorRepository.save(author);
        }
        return authorRetrived; // if author exist then return that otherwise create new one
    }
}
