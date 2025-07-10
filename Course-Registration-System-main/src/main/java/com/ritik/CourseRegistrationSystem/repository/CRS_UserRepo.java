package com.ritik.CourseRegistrationSystem.repository;

import com.ritik.CourseRegistrationSystem.model.CRS_User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CRS_UserRepo extends JpaRepository<CRS_User,Integer> {

    CRS_User findByEmail(String email);
}
