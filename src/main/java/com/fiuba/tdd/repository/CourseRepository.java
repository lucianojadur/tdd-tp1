package com.fiuba.tdd.repository;

import com.fiuba.tdd.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<Course,Integer> {

    Optional<Course> findById(Integer id);
}
