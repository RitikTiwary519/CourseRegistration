package com.ritik.CourseRegistrationSystem.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDTO {
    private String semesterName;
    private LocalDate startDate;
    private LocalDate endDate;
}
