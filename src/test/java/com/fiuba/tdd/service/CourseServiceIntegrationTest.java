package com.fiuba.tdd.service;

import com.fiuba.tdd.domain.Course;
import com.fiuba.tdd.domain.CreateCourseRequest;
import com.fiuba.tdd.repository.CourseRepository;
import com.fiuba.tdd.service.CourseService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)  // Use an actual database (in-memory)
@Transactional  // Rollback transactions to keep database clean after each test
public class CourseServiceIntegrationTest {

    @Autowired
    private CourseService service;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void e2eTest() {
        CreateCourseRequest newCourse = new CreateCourseRequest();
        newCourse.setTitle("test 1");
        newCourse.setDescription("description 1");
        
        Course createdCourse1 = service.addCourse(newCourse);

        // GET assertions including auto-generated ID for course 1
        assertEquals(1, createdCourse1.getId());
        assertEquals("test 1", createdCourse1.getTitle());
        assertEquals("description 1", createdCourse1.getDescription());
        
        
        // adding a 2nd course
        CreateCourseRequest secondCourse = new CreateCourseRequest();
        secondCourse.setTitle("test 2");
        secondCourse.setDescription("description 2");

        Course createdCourse2 = service.addCourse(secondCourse);

        // GET assertions including auto-generated ID for course 2
        assertEquals(2, createdCourse2.getId());
        assertEquals("test 2", createdCourse2.getTitle());
        assertEquals("description 2", createdCourse2.getDescription());

        // GET assertion for list of courses
        List<Course> courses = service.courses();
        assertEquals(2, courses.size());

        int count = courses.size();
        for (Course course : courses) {
            assertEquals(count, course.getId());
            count--;
        }

        service.deleteCourse(1);
        courses = service.courses();
        assertEquals(1, courses.size());
        assertEquals(2, courses.get(0).getId());
    }
}
