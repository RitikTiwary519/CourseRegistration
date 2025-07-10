package com.ritik.CourseRegistrationSystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(hidden = true)
public class Admin extends CRS_User {
    @Column(unique = true,nullable = false)
    private String adminCode;
}
