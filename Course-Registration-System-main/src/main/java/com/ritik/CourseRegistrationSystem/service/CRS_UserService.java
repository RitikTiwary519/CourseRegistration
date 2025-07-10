package com.ritik.CourseRegistrationSystem.service;

import com.ritik.CourseRegistrationSystem.DTO.CRS_UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CRS_UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager manager;
    public String login(CRS_UserDTO crs) {
        Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(crs.getEmail(),crs.getPassword()));
        if(auth.isAuthenticated())
            return jwtService.generateToken(crs);

        return "Invalid credentials";
    }
}