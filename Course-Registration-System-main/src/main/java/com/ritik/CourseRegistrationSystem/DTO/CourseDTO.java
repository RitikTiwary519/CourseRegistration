package com.ritik.CourseRegistrationSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private String courseName;
    private String courseCode;
    private int credits;
    private int capacity;
    private int remainingSeats;
    private Integer professorID;
    private Integer semesterID;
}
