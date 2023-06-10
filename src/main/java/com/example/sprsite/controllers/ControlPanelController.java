package com.example.sprsite.controllers;

import com.example.sprsite.models.Pizza;
import com.example.sprsite.models.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class ControlPanelController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/control/maincontrol")
    public String MainControl(Model model) {
        Iterable<Pizza> p = pizzaRepository.findAll();
        model.addAttribute("p" ,p);
        // model.addAttribute("title", "Title page");
        return "control/maincontrol";
    }

    @GetMapping("/control/Add")
    public String Add() {
        // model.addAttribute("title", "Title page");
        return "control/Add";
    }

    @PostMapping("/control/Add")
    public String Add(@RequestParam String name,@RequestParam String disc,@RequestParam double price,@RequestParam String img,Model model) {
        Pizza p = new Pizza(name,disc,price,img);
        pizzaRepository.save(p);
        return "redirect:/control/maincontrol";
    }
    @GetMapping("/control/Edit/{id}")
    public String Edit(Model model, @PathVariable long id) {

        Optional<Pizza> p = pizzaRepository.findById(id);
        ArrayList<Pizza> res = new ArrayList<>();
        p.ifPresent(res::add);
        model.addAttribute("p",res);
        return "/control/Edit";
    }
    @PostMapping("/control/Edit/{id}")
    public String Edit(@PathVariable long id, @RequestParam String name,@RequestParam String disc,@RequestParam double price,@RequestParam String img,Model model) {
        Pizza p = pizzaRepository.findById(id).orElseThrow();
        p.setName(name);
        p.setDisc(disc);
        p.setPrice(price);
        p.setImg(img);
        pizzaRepository.save(p);

        return "redirect:/control/maincontrol";
    }
    @PostMapping("/control/Delete/{id}")
    public String Delete(@PathVariable long id,Model model) {
        Pizza p = pizzaRepository.findById(id).orElseThrow();
        pizzaRepository.delete(p);

        return "redirect:/control/maincontrol";
    }
    @PostMapping("/control/search")
    public String search(@RequestParam String text, Model model) {
        Iterable<Pizza> p = pizzaRepository.FingByName(text);
        model.addAttribute("p" ,p);
        // model.addAttribute("title", "Title page");
        return "control/maincontrol";



    }


}
