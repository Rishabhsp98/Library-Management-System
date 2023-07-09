package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

}
