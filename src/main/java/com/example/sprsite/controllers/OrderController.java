package com.example.sprsite.controllers;

import com.example.sprsite.bean.HttpSessionBean;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


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
    @Autowired
    private final HttpSessionBean httpSessionBean;

    public OrderController(HttpSessionBean httpSessionBean) {
        this.httpSessionBean = httpSessionBean;
    }


    @PostMapping("/order/add/{id}")
    public String orderadd(Model model, @PathVariable long id, @AuthenticationPrincipal User user, @RequestParam int quantity) {

       if(httpSessionBean.getOrder() == null){
           httpSessionBean.setOrder(new Orders());
           if(user != null) {
               httpSessionBean.getOrder().setUser(user);
           }
           httpSessionBean.getOrder().setStatus("New");
       }

   Orders mainorder = httpSessionBean.getOrder();
       if(mainorder.getOrder_tovars() != null) {
           Set<Order_tovar> mainpizzas = mainorder.getOrder_tovars();


               for (Order_tovar i : mainpizzas) {
                   if (i.getPizza().getId() == id) {
                       int co = i.getCount() + quantity;
                       i.setCount(co);
                       return "redirect:/home";
                   }
               }

               Order_tovar ot = new Order_tovar();
               Optional<Pizza> p = pizzaRepository.findById(id);
               ot.setOrders(httpSessionBean.getOrder());
               ot.setCount(quantity);
               ot.setPizza(p.orElseThrow());
               mainpizzas.add(ot);


       }else{
           Order_tovar ot = new Order_tovar();
           Optional<Pizza> p = pizzaRepository.findById(id);
           ot.setOrders(httpSessionBean.getOrder());
           ot.setCount(quantity);
           ot.setPizza(p.orElseThrow());
           mainorder.setOrder_tovars(new HashSet<Order_tovar>());
           mainorder.getOrder_tovars().add(ot);
       }
        //model.addAttribute("p",res);

        return "redirect:/home";

    }

    @GetMapping("/order/cart")
    public String NewOrder(Model model,@AuthenticationPrincipal User user) {

        boolean has;
        if(httpSessionBean.getOrder() != null) {
            has = true;
            model.addAttribute("order", httpSessionBean.getOrder());
            model.addAttribute("total_price",String.format("%.2f", httpSessionBean.getOrder().getTotalPrice()));
        }else{has = false;}


            model.addAttribute("has", has);

            return "cart";


    }
    @PostMapping("/order/cart")
    public String SaveOrder(Model model,@AuthenticationPrincipal User user) {

        Orders mainorder = httpSessionBean.getOrder();
        if(user != null){
            mainorder.setUser(user);
        }
        mainorder.setStatus("Active");
        orderRepository.save(mainorder);
        httpSessionBean.setOrder(null);
        return "redirect:/home";

    }

    @GetMapping("/order/orders")
    public String GetUserOrders(Model model,@AuthenticationPrincipal User user) {

        Iterable<Orders> orders = orderRepository.getUserOrders(user.getId());
        model.addAttribute("order",orders);
        return "orders";

    }
}
