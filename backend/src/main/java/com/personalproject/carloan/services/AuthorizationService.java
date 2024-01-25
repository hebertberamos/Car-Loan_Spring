package com.personalproject.carloan.services;

import com.personalproject.carloan.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Help Spring Security identify how user authentication should be done
@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    // Way to consult users every time a User tries to authenticate in the application
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }
}
