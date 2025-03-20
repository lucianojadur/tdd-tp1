package com.fiuba.tdd.controller;


import com.fiuba.tdd.domain.Course;
import com.fiuba.tdd.domain.CreateCourseRequest;
import com.fiuba.tdd.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService service;


    @PostMapping(path = "/courses", consumes = "application/json", produces = "application/json")
    public Course post(@RequestBody CreateCourseRequest newCourse) {
        return service.addCourse(newCourse);
    }

    @GetMapping(path = "/courses", produces = "application/json")
    public List<Course> getCourses() {
        return service.courses();
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
