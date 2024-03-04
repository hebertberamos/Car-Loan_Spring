package com.personalproject.carloan.services;

import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.repositories.UserRepository;
import com.personalproject.carloan.services.exceptions.ForbiddenException;
import com.personalproject.carloan.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Help Spring Security identify how user authentication should be done
@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    // Way to consult users every time a User tries to authenticate in the application
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

    // =>  Return logged user
    @Transactional(readOnly = true)
    public User authenticated(){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return repository.findByEmail(username);
        }
        catch(Exception e){
            throw new UnauthorizedException("Inválid user");
        }
    }

    // =>  Identify if User are self id
    public void validateSelf(Long id){
        User user = authenticated();
        if(!user.getId().equals(id)){
            throw new ForbiddenException("Access danied");
        }
    }

    // =>  Method to identify if User are self id or ADMIN
    public void validateSelfOrAdmin(Long id){
        User user = authenticated();

        if(!user.getId().equals(id) && !user.hasRoleAdmin() == true){
            throw new ForbiddenException("Access danied");
        }
    }
}
