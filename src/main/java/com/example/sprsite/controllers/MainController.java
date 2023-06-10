package com.example.sprsite.controllers;

import com.example.sprsite.models.Pizza;
import com.example.sprsite.models.User;
import com.example.sprsite.models.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/")
    public String loghome(Model model) {

        return "redirect:/home";

    }
    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal User user) {
        if(user != null) {
            model.addAttribute("Username", user.getUsername());
        }
        Iterable<Pizza> p = pizzaRepository.findAll();
        model.addAttribute("p" ,p);
        return "Home";

    }

    @PostMapping("/search")
    public String search(@RequestParam String text, Model model) {
        Iterable<Pizza> p = pizzaRepository.FingByName(text);
        model.addAttribute("p" ,p);
        // model.addAttribute("title", "Title page");
        return "home";



    }

}