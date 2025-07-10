package com.ritik.CourseRegistrationSystem.Mappers;

import com.ritik.CourseRegistrationSystem.DTO.CourseDTO;
import com.ritik.CourseRegistrationSystem.model.Course;
import com.ritik.CourseRegistrationSystem.model.Professor;
import com.ritik.CourseRegistrationSystem.model.Semester;
import com.ritik.CourseRegistrationSystem.repository.ProfessorRepo;
import com.ritik.CourseRegistrationSystem.repository.SemesterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {

    @Autowired
    private ProfessorRepo prepo;

    @Autowired
    private SemesterRepo semrepo;
    public Course toEntity(CourseDTO coursedto)
    {
        if(coursedto == null)
            return null;
        Course course = new Course();
        course.setCourseName(coursedto.getCourseName());
        course.setCapacity(coursedto.getCapacity());
        course.setRemainingSeats(coursedto.getRemainingSeats());
        course.setCredits(coursedto.getCredits());
        course.setCourseCode(coursedto.getCourseCode());

        Professor professor = prepo.findById(coursedto.getProfessorID()).orElse(null);

        if(professor==null)
        {
            System.out.println("Professor not found");
            throw new RuntimeException("Professor not found");
        }
        course.setProfessor(professor);

        Semester semester = semrepo.findById(coursedto.getSemesterID()).orElse(null);

        if(semester==null)
        {
            System.out.println("Semester not found");
            throw new RuntimeException("Semester not found");
        }
        List<Semester> semList= new ArrayList<>();
        semList.add(semester);
        course.setSemesterList(semList);

        return course;
    }

    public CourseDTO toDto(Course course) {
        if (course == null) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCredits(course.getCredits());
        courseDTO.setCapacity(course.getCapacity());
        courseDTO.setRemainingSeats(course.getRemainingSeats());
        courseDTO.setCourseCode(course.getCourseCode());

        if (course.getProfessor() != null) {
            courseDTO.setProfessorID(course.getProfessor().getId());
        }

        if (course.getSemesterList() != null && !course.getSemesterList().isEmpty()) {

            courseDTO.setSemesterID(course.getSemesterList().get(0).getId());
        }

        return courseDTO;
    }

}
