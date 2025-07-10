package com.ritik.CourseRegistrationSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(hidden = true)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String courseName;
    @Column(unique = true,nullable = false)
    private String courseCode;
    @Column(nullable = false)
    private int credits;
    private int capacity;
    private int remainingSeats;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Professor professor;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Semester> semesterList;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Student> enrolledStudents;


}
