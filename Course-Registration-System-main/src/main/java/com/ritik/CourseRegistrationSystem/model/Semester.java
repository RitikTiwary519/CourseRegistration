package com.ritik.CourseRegistrationSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(hidden = true)
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true,nullable = false)
    private String semesterName;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "semesterList",fetch = FetchType.EAGER)
    private List<Course> courseList;

    @JsonIgnore
    @OneToMany(mappedBy = "currentSemester",fetch = FetchType.EAGER)
    private List<Student> registeredStudents;
}
