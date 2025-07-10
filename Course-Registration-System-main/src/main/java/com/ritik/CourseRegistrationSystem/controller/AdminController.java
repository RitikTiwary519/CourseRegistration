package com.ritik.CourseRegistrationSystem.controller;

import com.jeetdesaimusic.CourseRegistrationSystem.DTO.*;
import com.jeetdesaimusic.CourseRegistrationSystem.model.*;
import com.ritik.CourseRegistrationSystem.DTO.*;
import com.ritik.CourseRegistrationSystem.model.*;
import com.ritik.CourseRegistrationSystem.service.AdminService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;
    @GetMapping("/greet")
    public String greet()
    {
        return "Kem chho admin bhai";
    }



    @PostMapping("/register")
    public Admin addAdmin(@RequestBody AdminDTO myAdmin)
    {
        if(myAdmin==null)
            return null;
        return service.registerAdmin(myAdmin);
    }

    @PostMapping("/add-student")
    public Student addStudent(@RequestBody StudentDTO studentdto)
    {
        if(studentdto==null)
            return null;
        return service.registerStudent(studentdto);
    }

    @PostMapping("/add-professor")
    public Professor addStudent(@RequestBody ProfessorDTO professordto)
    {
        if(professordto==null)
            return null;
        return service.registerProfessor(professordto);
    }

//    @Operation(
//            summary = "Add a semester",
//            parameters = {
//                    @Parameter(
//                            name = "X-CSRF-TOKEN",
//                            in = ParameterIn.HEADER,
//                            required = true,
//                            description = "CSRF Token for authentication"
//                    )
//            }
//    )

//    @Operation(
//            summary = "Register for a course",
//            description = "Register a student for a course using the course ID."
//    )
    @PostMapping("/add-semester")
    public Semester addSemester(@RequestBody SemesterDTO semesterdto)
    {
        if(semesterdto==null)
            return null;
        return service.registerSemester(semesterdto);
    }


    @PostMapping("/add-course")
    public Course addCourse(@RequestBody CourseDTO coursedto)
    {
        if(coursedto==null)
            return null;
        return service.registerCourse(coursedto);
    }

    @PutMapping("/update-course/{cid}")
    public Course updateCourse(@PathVariable Integer cid,@RequestBody CourseDTO coursedto)
    {
        if(coursedto==null)
            return null;
        return service.updateCourse(cid,coursedto);
    }

    @PutMapping("/update-semester/{sid}")
    public Semester updateSemester(@PathVariable Integer sid,@RequestBody SemesterDTO semesterdto)
    {
        if(semesterdto==null)
            return null;
        return service.updateSemester(sid,semesterdto);
    }

    @DeleteMapping("/delete-student/{sid}")
    public String deleteStudent(@PathVariable Integer sid)
    {

        return service.deleteStudent(sid);
    }

    @DeleteMapping("/delete-professor/{pid}")
    public String deleteProfessor(@PathVariable Integer pid)
    {
        return service.deleteProfessor(pid);
    }

    @DeleteMapping("/delete-course/{cid}")
    public String deleteCourse(@PathVariable Integer cid)
    {
        return service.deleteCourse(cid);
    }

//    @GetMapping("/csrf-token")
//    public CsrfToken getCSRF(HttpServletRequest req)
//    {
//        return (CsrfToken) req.getAttribute("_csrf");
//    }

    @GetMapping("/allocate-courses")
    public boolean assignCourses()
    {
        return service.assignCourses();
    }

    @GetMapping("/send-reminder/{semesterName}/deadline/{dl}")
    public void sendReminder(@PathVariable String semesterName,@PathVariable String dl)
    {
        service.sendReminder(semesterName,dl);
    }



}
