package com.ritik.CourseRegistrationSystem.controller;

import com.ritik.CourseRegistrationSystem.DTO.CRS_UserDTO;
import com.ritik.CourseRegistrationSystem.service.CRS_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CRS_UserController {
    @Autowired
    private CRS_UserService service;

    @PostMapping("/login")
    public String login(@RequestBody CRS_UserDTO crs)
    {
        return service.login(crs);
    }
}
