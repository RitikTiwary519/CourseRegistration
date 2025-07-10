package com.ritik.CourseRegistrationSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationFormDTO {

    private List<String> preferenceList;
    private Integer requiredCourses;
}
