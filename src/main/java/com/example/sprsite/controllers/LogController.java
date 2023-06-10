package com.example.sprsite.controllers;

import com.example.sprsite.models.Role;
import com.example.sprsite.models.User;
import com.example.sprsite.models.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;


@Controller
public class LogController {
@Autowired
private UserRepository userRepo;
//    public class First {
//        public static <T> T getFirstElementOf(Iterable<T> iterable) {
//            return iterable.iterator().next();
//        }/*  w  w  w.java2 s.  c om*/
//    }
//    @Autowired
//    private PersonRepository personRepository;
//
    @GetMapping("/registration")
    public String reg() {

        return "Registration";
    }
    @PostMapping("/registration")
    public String Register(User user, Map<String, Object> model) {

        User UserDB = userRepo.findByUsername(user.getUsername());

        if (UserDB != null) {
              model.put("Message","User exists!");
              return "Registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);


        return "redirect:/login";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String login,@RequestParam String pass,Model model) {
//        // model.addAttribute("title", "Title page");
//        Iterable<Person> p = personRepository.FingByLogin(login);
//        Person per = First.getFirstElementOf(p);
//        if(per == null){
//
//            return "redirect:/login";
//        }
//        else{
//            if(pass.equals(per.getPass())){
//                return "redirect:/home";
//            }
//            else{
//                return "redirect:/login";
//            }
//
//        }
//
//    }
//
//    @GetMapping("/Register")
//    public String Register() {
//        // model.addAttribute("title", "Title page");
//        return "Register";
//    }
//    @PostMapping("/Register")
//    public String Register(@RequestParam String login,@RequestParam String pass ,Model model) {
//        // model.addAttribute("title", "Title page");
//        Person per = new Person(login, pass, false);
//        personRepository.save(per);
//        return "redirect:/login";
//    }
}
