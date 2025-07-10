package com.ritik.CourseRegistrationSystem.repository;
import com.ritik.CourseRegistrationSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
}
