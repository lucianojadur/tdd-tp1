package com.fiuba.tdd.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Course {

    @Id
    private Integer id;
    private String title;
    private String description;
}
