package com.java.springbootrestapi.controller;

import com.java.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // http://localhost:8080/student

    @GetMapping("student")
    public Student getStudent() {
        Student student = new Student(
                1,
                "kong",
                "sachakyarith"
        );
        return student;
    }

    // http://localhost:8080/students
    @GetMapping("students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "sok", "dara"));
        students.add(new Student(2, "kong", "ka"));
        students.add(new Student(3, "keo", "kay"));
        students.add(new Student(3, "sam", "rina"));
        return students;
    }

    // Spring Rest Api with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/students/1/sok/dara
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firsName,
                                       @PathVariable("last-name") String lastName) {
        return new Student(studentId, firsName, lastName);
    }

    // Spring boot  REST API with Request Para
    // http://localhost:8080/students/query?id=1&firstName=soks&lastName=daras
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {
        return new Student(id, firstName, lastName);
    }

    // Spring boot REST API that handles HTTP POST Request
    // @PostMapping and @RequestBody
    // http://localhost:8080/students/create
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println("id : " + student.getId());
        System.out.println("firstName : " + student.getFirstName());
        System.out.println("lastName : " + student.getLastName());

        return student;
    }
}
