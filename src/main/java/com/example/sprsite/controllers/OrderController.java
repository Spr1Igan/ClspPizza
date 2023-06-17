package com.example.sprsite.controllers;

import com.example.sprsite.models.Order_tovar;
import com.example.sprsite.models.Orders;
import com.example.sprsite.models.Pizza;
import com.example.sprsite.models.User;
import com.example.sprsite.models.repo.OrderRepository;
import com.example.sprsite.models.repo.PizzaRepository;
import com.example.sprsite.models.repo.UserRepository;
import com.example.sprsite.models.repo.order_tovRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@Controller
public class OrderController {

    @Autowired
    private order_tovRepository tovrep;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("/order/add/{id}")
    public String orderadd(Model model, @PathVariable long id) {
        Order_tovar ot = new Order_tovar();
        Optional<Pizza> p = pizzaRepository.findById(id);
        long a = 1;
      ot.setOrders(orderRepository.findById(a).orElseThrow());
      ot.setCount(1);
      ot.setPizza(p.orElseThrow());
        tovrep.save(ot);
        //model.addAttribute("p",res);

        return "redirect:/home";

    }

    @GetMapping("/order/create")
    public String NewOrder(Model model,@AuthenticationPrincipal User user) {
       //long a = 1;
        //Orders or = orderRepository.findById(a).orElseThrow();
        Orders or = new Orders();
        or.setStatus("New");
        if(user != null) {
           or.setUser(user);
        }

        orderRepository.save(or);
        //model.addAttribute("p",res);

        return "redirect:/home";

    }
}
