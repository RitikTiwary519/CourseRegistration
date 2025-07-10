package com.ritik.CourseRegistrationSystem.service;

import com.jeetdesaimusic.CourseRegistrationSystem.DTO.*;
import com.ritik.CourseRegistrationSystem.DTO.CourseForStudentDTO;
import com.ritik.CourseRegistrationSystem.DTO.RegistrationFormDTO;
import com.ritik.CourseRegistrationSystem.DTO.StudentDTO;
import com.ritik.CourseRegistrationSystem.DTO.StudentOutDTO;
import com.ritik.CourseRegistrationSystem.model.Course;
import com.ritik.CourseRegistrationSystem.model.RegistrationForm;
import com.ritik.CourseRegistrationSystem.model.Student;
import com.ritik.CourseRegistrationSystem.repository.CourseRepo;
import com.ritik.CourseRegistrationSystem.repository.RegistrationFormRepo;
import com.ritik.CourseRegistrationSystem.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo srepo;

    @Autowired
    private RegistrationFormRepo rfrepo;

    @Autowired
    private CourseRepo crepo;

    public StudentOutDTO getStudent(Integer id)
    {
        Student student = srepo.findById(id).orElseThrow(()->new RuntimeException("Student not found"));
        StudentOutDTO studentdto = new StudentOutDTO();
        studentdto.setName(student.getName());
        studentdto.setStudentID(student.getStudentID());
        studentdto.setEmail(student.getEmail());
        return studentdto;
    }

    public String updateStudent(Integer id, StudentDTO studentDTO) {
        Student student = srepo.findById(id).orElseThrow(()->new RuntimeException("Student not found"));
        student.setName(studentDTO.getName());
        student.setPassword(studentDTO.getPassWord());
        student.setEmail(studentDTO.getEmail());
        srepo.save(student);
        return "Successfully updated";
    }

    public String submitForm(Integer id, RegistrationFormDTO registrationFormDTO) {
        Student student = srepo.findById(id).orElseThrow(()->new RuntimeException("Student not found"));
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setSid(id);
        registrationForm.setPreferenceList(registrationFormDTO.getPreferenceList());
        registrationForm.setRequiredCourses(registrationFormDTO.getRequiredCourses());
        registrationForm.setSubmissionTime(LocalDateTime.now());
        rfrepo.save(registrationForm);
        return "Submitted Successfully";

    }

    public List<CourseForStudentDTO> getAllCourses() {
        List<Course> courseList = crepo.findAll();
        List<CourseForStudentDTO> courses = new ArrayList<>();
        for(Course c:courseList)
        {
            CourseForStudentDTO cs = new CourseForStudentDTO();
            cs.setCourseName(c.getCourseName());
            cs.setCourseCode(c.getCourseCode());
            cs.setCredits(c.getCredits());
            cs.setProfessorCode(c.getProfessor().getProfCode());
            courses.add(cs);
        }
        return courses;
    }
}
