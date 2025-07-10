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
public class Professor extends CRS_User {
    @Column(unique = true,nullable = false)
    private String profCode;

    @JsonIgnore
    @OneToMany(mappedBy = "professor",fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Course> courseList;

}
