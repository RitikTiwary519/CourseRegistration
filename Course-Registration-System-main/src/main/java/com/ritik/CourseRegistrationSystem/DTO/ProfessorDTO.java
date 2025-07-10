package com.ritik.CourseRegistrationSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfessorDTO {
    private String name;
    private String email;
    private String passWord;
    private String profCode;
}
