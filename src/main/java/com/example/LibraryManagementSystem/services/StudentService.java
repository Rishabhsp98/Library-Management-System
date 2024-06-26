package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.dtos.CreateStudentRequest;
import com.example.LibraryManagementSystem.models.Student;
import com.example.LibraryManagementSystem.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    private static Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    StudentRepository studentRepository;

    public void create(Student student) {
        logger.info("Student object, {}",student);
        studentRepository.save(student);
    }

    public Student find(int studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public void update(Student student, int id) {
        Student studentfromdb = studentRepository.findById(id).orElse(null);
        if(studentfromdb != null)
        {
            studentfromdb.setName(student.getName());
            studentfromdb.setEmail(student.getEmail());
            studentfromdb.setAge(student.getAge());

            logger.info("Data update for the student {}",student);
        }
        else
            logger.info("Entry does not exists in the Database");
    }


    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }
}
