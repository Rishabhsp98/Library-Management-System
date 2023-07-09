package com.example.LibraryManagementSystem.dtos;

import com.example.LibraryManagementSystem.models.Admin;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAdminRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    public Admin to(){
        return Admin.builder().
                name(this.name).
                email(this.email)
                .build();
    }
}
