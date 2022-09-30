package com.example.Spring_Security_Tutoiral.student;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/student")
public class StudentManagementController {

    private final static List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James"),
            new Student(2, "Maria"),
            new Student(3, "Anna")
    );

    @GetMapping
    public List<Student> getAllStudents()
    {
        System.out.println("getAllStudents");
        return STUDENTS;
    }

    @PostMapping
    public void registerStudents(@RequestBody Student student)
    {
        System.out.println("registerStudents");
        System.out.println(student);
    }

    @DeleteMapping(path = {"studentId"})
    public void deleteStudents(@PathVariable("studentId") Integer studentId)
    {
        System.out.println("deleteStudents");
        System.out.println(studentId);
    }

    @PutMapping(path = {"studentId"})
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student)
    {
        System.out.println("updateStudent");
        System.out.println(String.format("%s %s", student, student));
    }

}
