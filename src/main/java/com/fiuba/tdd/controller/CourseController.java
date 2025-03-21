package com.fiuba.tdd.controller;


import com.fiuba.tdd.domain.Course;
import com.fiuba.tdd.domain.CreateCourseRequest;
import com.fiuba.tdd.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Course getCourse(@PathVariable("id") Integer id) {
        return service.getCourse(id);
    }

    @DeleteMapping(path = "/courses/{id}")
    public void deleteCourse(@PathVariable("id") Integer id) {
        try{
            service.deleteCourse(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
