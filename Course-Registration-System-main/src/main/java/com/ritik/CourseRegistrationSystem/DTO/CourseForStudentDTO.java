package com.ritik.CourseRegistrationSystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseForStudentDTO {
    private String courseName;
    private String courseCode;
    private String professorCode;
    private Integer credits;

}
