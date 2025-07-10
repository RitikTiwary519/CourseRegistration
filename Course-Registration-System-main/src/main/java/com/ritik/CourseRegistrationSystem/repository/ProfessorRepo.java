package com.ritik.CourseRegistrationSystem.repository;
import com.ritik.CourseRegistrationSystem.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepo extends JpaRepository<Professor,Integer> {
}
