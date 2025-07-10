package com.ritik.CourseRegistrationSystem.repository;

import com.ritik.CourseRegistrationSystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {
}
