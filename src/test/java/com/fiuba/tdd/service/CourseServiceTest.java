package com.fiuba.tdd.service;

import com.fiuba.tdd.domain.Course;
import com.fiuba.tdd.domain.CreateCourseRequest;
import com.fiuba.tdd.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    public void testAddCourse() {
        CreateCourseRequest newCourse = new CreateCourseRequest();
        newCourse.setTitle("test course");
        newCourse.setDescription("desc");

        Course mockCourse = new Course();
        mockCourse.setTitle(newCourse.getTitle());
        mockCourse.setDescription(newCourse.getDescription());

        when(courseRepository.save(any(Course.class))).thenReturn(mockCourse);

        Course createdCourse = courseService.addCourse(newCourse);

        assertNotNull(createdCourse);
        assertEquals("test course", createdCourse.getTitle());
        assertEquals("desc", createdCourse.getDescription());

        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    public void testGetCourseById() {
        Course mockCourse = new Course();
        mockCourse.setTitle("Test Course");
        mockCourse.setDescription("Description");

        when(courseRepository.findById(1)).thenReturn(Optional.of(mockCourse));

        Course foundCourse = courseService.getCourse(1);

        assertNotNull(foundCourse);
        assertEquals("Test Course", foundCourse.getTitle());
        assertEquals("Description", foundCourse.getDescription());
    }

    @Test
    public void testDeleteCourse() {
        Integer courseId = 1;
        courseService.deleteCourse(courseId);
        verify(courseRepository, times(1)).deleteById(courseId);
    }
}
