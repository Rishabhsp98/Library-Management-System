package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.dtos.CreateAdminRequest;
import com.example.LibraryManagementSystem.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {


    @Autowired
    AdminService adminService;
    @PostMapping("/admin")
    public void createAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest){
        adminService.create(createAdminRequest.to());
    }

    @GetMapping("/admin")
    public String helloWorld(){
        return "Hello World";
    }

}
