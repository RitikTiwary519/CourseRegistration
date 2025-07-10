package com.ritik.CourseRegistrationSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseOfProfessorDTO {
    private String courseName;
    private int credits;
}
