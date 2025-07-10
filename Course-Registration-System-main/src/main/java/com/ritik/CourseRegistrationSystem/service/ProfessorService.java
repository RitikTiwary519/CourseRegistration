package com.ritik.CourseRegistrationSystem.service;

import com.ritik.CourseRegistrationSystem.DTO.CourseOfProfessorDTO;
import com.ritik.CourseRegistrationSystem.DTO.ProfessorDTO;
import com.ritik.CourseRegistrationSystem.model.Course;
import com.ritik.CourseRegistrationSystem.model.Professor;
import com.ritik.CourseRegistrationSystem.repository.ProfessorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepo prepo;

    public ProfessorDTO getProfessor(Integer id)
    {
        Professor professor = prepo.findById(id).orElseThrow(()->new RuntimeException("Professor not found"));
        ProfessorDTO professordto = new ProfessorDTO();
        professordto.setName(professor.getName());
        professordto.setEmail(professor.getEmail());
        professordto.setPassWord(professor.getPassword());
        professordto.setProfCode(professor.getProfCode());
        return professordto;
    }

    public String updateProfessor(Integer id, ProfessorDTO professorDTO) {
        Professor professor = prepo.findById(id).orElseThrow(()->new RuntimeException("Professor not found"));
        professor.setName(professorDTO.getName());
        professor.setPassword(professorDTO.getPassWord());
        professor.setEmail(professorDTO.getEmail());
        prepo.save(professor);
        return "Successfully updated";
    }

    public List<CourseOfProfessorDTO> getCourses(Integer id) {
        Professor professor = prepo.findById(id).orElseThrow(()->new RuntimeException("Professor not found"));
        List<Course> courses = professor.getCourseList();
        List<CourseOfProfessorDTO> courseDTOList = new ArrayList<>();
        for(Course c : courses)
        {
            CourseOfProfessorDTO cdto = new CourseOfProfessorDTO();
            cdto.setCourseName(c.getCourseName());
            cdto.setCredits(c.getCredits());
            courseDTOList.add(cdto);
        }
        return courseDTOList;

    }
}
