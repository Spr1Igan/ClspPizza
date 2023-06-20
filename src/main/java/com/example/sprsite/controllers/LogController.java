package com.example.sprsite.controllers;

import com.example.sprsite.models.Role;
import com.example.sprsite.models.User;
import com.example.sprsite.models.repo.UserRepository;
import com.example.sprsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;


@Controller
public class LogController {


    @Autowired
    private UserService userService;
    @GetMapping("/registration")
    public String reg() {

        return "Registration";
    }
    @PostMapping("/registration")
    public String Register(User user, Map<String, Object> model) {

        boolean UserDB = userService.addUser(user);

        if (UserDB == false) {
              model.put("Message","User exists!");
              return "Registration";
        }

        return "redirect:/login";
    }

}
