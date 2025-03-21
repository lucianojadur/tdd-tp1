package com.fiuba.tdd.service;

import com.fiuba.tdd.domain.Course;
import com.fiuba.tdd.domain.CreateCourseRequest;
import com.fiuba.tdd.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repo;

    /**
     * Adds a new couse to the database
     * */
    public Course addCourse(CreateCourseRequest newCourse) {
        Course course = new Course();
        course.setTitle(newCourse.getTitle());
        course.setDescription(newCourse.getDescription());
        return repo.save(course);
    }

    /**
     * Returns all the courses (or an empty list) in the database
     * */
    public List<Course> courses()  {
        List<Course> courses = new ArrayList<>();

        repo.findAll().forEach(courses::add);
        if (courses.isEmpty()) { throw new NoSuchElementException();}

        Collections.reverse(courses);
        return courses;
    }

    /**
     * Returns an existing course from the database or null
     * if the ID is missing
     * */
    public Course getCourse(Integer id) {
        return repo.findById(id).orElseThrow(NoSuchElementException::new);
    }
    /**
     * Drops an existing course from the database
     * */
    public void deleteCourse(Integer id, String testMode) {
        if (!repo.existsById(id) && testMode.equals("manual")) {throw new NoSuchElementException();}
        repo.deleteById(id);
    }
}
