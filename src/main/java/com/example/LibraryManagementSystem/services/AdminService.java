package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.dtos.CreateAdminRequest;
import com.example.LibraryManagementSystem.models.Admin;
import com.example.LibraryManagementSystem.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public void create(Admin admin) {
        if(admin != null) {
            adminRepository.save(admin);
        }
    }

    public Admin find(Integer adminId){

        if(adminId != null) {
            return  adminRepository.findById(adminId).orElse(null);
        }
        return null;
    }

}
