package com.dj.studentms.service;

import com.dj.studentms.model.Student;
import com.dj.studentms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    public Boolean isStudentExist(Long id) {
        return studentRepository.existsById(id);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
