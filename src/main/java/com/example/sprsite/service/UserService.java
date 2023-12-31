package com.example.sprsite.service;

import com.example.sprsite.models.Role;
import com.example.sprsite.models.User;
import com.example.sprsite.models.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);


        return true;
    }
}
