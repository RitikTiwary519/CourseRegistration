package com.ritik.CourseRegistrationSystem.repository;
import com.ritik.CourseRegistrationSystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {
    Course findByCourseCode(String coursecode);

}
