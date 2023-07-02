package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.dtos.CreateStudentRequest;
import com.example.LibraryManagementSystem.models.Student;
import com.example.LibraryManagementSystem.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StudentController {


    @Autowired
    StudentService studentService;
    @PostMapping("/student")
    public void createStudent(@RequestBody @Valid CreateStudentRequest studentRequest){
        studentService.create(studentRequest.to());
    }

    @GetMapping("/student")
    public Student findStudent(@PathVariable Integer id)
    {
        return studentService.find(id);
    }

    @PutMapping("/student/{id}")
    public void updateStudent(@RequestBody CreateStudentRequest studentRequest,@PathVariable Integer id)
    {
        studentService.update(studentRequest.to(),id);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Integer id)
    {
        studentService.delete(id);
    }


}

