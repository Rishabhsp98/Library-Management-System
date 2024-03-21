package com.example.LibraryManagementSystem.dtos;

import com.example.LibraryManagementSystem.models.Author;
import com.example.LibraryManagementSystem.models.Book;
import com.example.LibraryManagementSystem.models.Genre;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest {

    @NotBlank
    private String name;

    @NotNull
    private Genre genre;

    @NotBlank
    private String authorName;

    @NotBlank
    private String authorEmail;


    public Book to(){

        Author authorObj = Author.builder()
                .name(this.authorName)
                .email(this.authorEmail)
                .build();
        return Book.builder()
                        .name(this.name)
                        .genre(this.genre)
                        .my_author(authorObj).
                        build();
    }
}
