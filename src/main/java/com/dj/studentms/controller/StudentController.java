package com.dj.studentms.controller;

import com.dj.studentms.model.Student;
import com.dj.studentms.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {

        Student savedStudent = studentService.addStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudent(id);
        if(student.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Student not found"
            );
        }
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentService.isStudentExist(id)) {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Optional<Student> optionalStudent = studentService.getStudent(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setFname(updatedStudent.getFname());
            existingStudent.setLname(updatedStudent.getLname());
            existingStudent.setAge(updatedStudent.getAge());
            Student savedStudent = studentService.addStudent(existingStudent);
            return new ResponseEntity<>(savedStudent, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@Value("${server.instance.id}")
    String instanceId;*/

    @GetMapping("/hello")
    public String hello() {
        return String.format("Hello from instance %s", 1);
    }
}


