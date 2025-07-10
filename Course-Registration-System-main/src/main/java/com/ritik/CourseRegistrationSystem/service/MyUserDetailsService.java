package com.ritik.CourseRegistrationSystem.service;

import com.ritik.CourseRegistrationSystem.model.CRS_User;
import com.ritik.CourseRegistrationSystem.repository.CRS_UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {






    @Autowired
    private CRS_UserRepo crsrepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CRS_User crsu = crsrepo.findByEmail(email);
        if(crsu==null)
        {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(crsu);
    }
}
