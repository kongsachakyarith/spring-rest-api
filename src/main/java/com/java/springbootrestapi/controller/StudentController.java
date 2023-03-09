package com.java.springbootrestapi.controller;

import com.java.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student

    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
                1,
                "kong",
                "sachakyarith"
        );
//        return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok().header("custom-header", "kong").body(student);
    }

    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "sok", "dara"));
        students.add(new Student(2, "kong", "ka"));
        students.add(new Student(3, "keo", "kay"));
        students.add(new Student(3, "sam", "rina"));
        return ResponseEntity.ok(students);
    }

    // Spring Rest Api with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/students/1/sok/dara
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firsName,
                                       @PathVariable("last-name") String lastName) {
        Student student =  new Student(studentId, firsName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring boot  REST API with Request Para
    // http://localhost:8080/students/query?id=1&firstName=soks&lastName=daras
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {
        Student student =  new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API that handles HTTP POST Request
    // @PostMapping and @RequestBody
    // http://localhost:8080/students/create
    @PostMapping("create")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println("id : " + student.getId());
        System.out.println("firstName : " + student.getFirstName());
        System.out.println("lastName : " + student.getLastName());

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring boot REST API that handles HTTP PUT Request - update exising resource
    // http://localhost:8080/students/1/update
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // Spring boot REST API that handles HTTP DELETE Request - delete the resource
    // http://localhost:8080/students/1/delete
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return new ResponseEntity<>("Student deleted successfully!", HttpStatus.OK);
    }
}
