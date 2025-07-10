package com.ritik.CourseRegistrationSystem.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private String name;
    private String email;
    private String passWord;
    private String studentID;
    private String semesterName;
    private Integer semesterNo;
    private Double cpi;

}
