package com.spring.jwt.jwtrevision.config;


import com.spring.jwt.jwtrevision.model.User;
import com.spring.jwt.jwtrevision.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPricipleDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public UserPricipleDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        UserPrinciple userPrinciple = new UserPrinciple(user);
        return userPrinciple;
    }
}
