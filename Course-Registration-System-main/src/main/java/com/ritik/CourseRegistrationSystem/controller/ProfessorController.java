package com.ritik.CourseRegistrationSystem.controller;

import com.ritik.CourseRegistrationSystem.DTO.CourseOfProfessorDTO;
import com.ritik.CourseRegistrationSystem.DTO.ProfessorDTO;
import com.ritik.CourseRegistrationSystem.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @GetMapping("/get-info/{id}")
    public ProfessorDTO getProfessor(@PathVariable Integer id)
    {
        return service.getProfessor(id);
    }

    @PutMapping("/update/{id}")
    public String updateProfessor(@PathVariable Integer id,@RequestBody ProfessorDTO professorDTO)
    {
        return service.updateProfessor(id,professorDTO);
    }

    @GetMapping("get-courses/{id}")
    public List<CourseOfProfessorDTO> getCourses(@PathVariable Integer id)
    {
        return service.getCourses(id);
    }



}
