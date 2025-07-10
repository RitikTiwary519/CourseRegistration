package com.ritik.CourseRegistrationSystem.repository;


import com.ritik.CourseRegistrationSystem.model.RegistrationForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationFormRepo extends JpaRepository<RegistrationForm,Integer> {
}
