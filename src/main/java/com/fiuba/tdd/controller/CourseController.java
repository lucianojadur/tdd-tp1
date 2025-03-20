package com.fiuba.tdd.controller;


import com.fiuba.tdd.domain.Course;
import com.fiuba.tdd.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService service;


    @PostMapping(path = "/courses", consumes = "application/json", produces = "application/json")
    public Course post(@RequestBody Course course) {
        return service.addCourse(course);
    }

    @GetMapping(path = "/courses", produces = "application/json")
    public List<Course> getCourses() {
        return service.courses();
    }

    @GetMapping(path = "/courses/{id}", produces = "application/json")
    public Course getCourse(@PathVariable("id") Integer id) {
        return service.getCourse(id);
    }
}
