package com.fiuba.tdd.controller;


import com.fiuba.tdd.domain.Course;
import com.fiuba.tdd.domain.CreateCourseRequest;
import com.fiuba.tdd.domain.ErrorResponse;
import com.fiuba.tdd.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CourseController {

    @Autowired
    private CourseService service;


    @GetMapping(path = "/")
    public String home() {
        return "¡¡Bienvendidos a ClassConnect!!";
    }

    @PostMapping(path = "/courses", consumes = "application/json", produces = "application/json")
    public Course post(@RequestBody CreateCourseRequest newCourse) {
        return service.addCourse(newCourse);
    }

    @GetMapping(path = "/courses", produces = "application/json")
    public List<Course> getCourses() {
        try{
            return service.courses();
        } catch (NoSuchElementException e) {
            return new ArrayList<>();
        }
    }

    @GetMapping(path = "/courses/{id}", produces = "application/json")
    public Course getCourse(@PathVariable("id") Integer id) throws NoSuchElementException {
        return service.getCourse(id);
    }

    @DeleteMapping(path = "/courses/{id}")
    public void deleteCourse(@PathVariable("id") Integer id) throws NoSuchElementException {
            service.deleteCourse(id, "manual");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleCourseNotFound(NoSuchElementException ex, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        String idParam = servletWebRequest.getRequest().getRequestURI();
        String id = idParam.substring(idParam.lastIndexOf("/") + 1);

        ErrorResponse error = new ErrorResponse();
        error.setTitle("Course Not Found");
        error.setStatus(HttpStatus.NOT_FOUND.toString());
        error.setDetail(ex.getMessage());
        error.setInstance("/courses/" + id);

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleServerError(Exception ex, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        String idParam = servletWebRequest.getRequest().getRequestURI();
        String id = idParam.substring(idParam.lastIndexOf("/") + 1);

        ErrorResponse error = new ErrorResponse();
        error.setTitle("Internal Server Error");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        error.setDetail(ex.getMessage());
        error.setInstance("/" + id);

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
