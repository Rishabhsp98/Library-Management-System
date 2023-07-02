package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
