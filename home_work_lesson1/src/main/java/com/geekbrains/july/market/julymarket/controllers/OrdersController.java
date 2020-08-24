package com.geekbrains.july.market.julymarket.controllers;


import com.geekbrains.july.market.julymarket.basket.Basket;
import com.geekbrains.july.market.julymarket.entities.Order;
import com.geekbrains.july.market.julymarket.entities.User;
import com.geekbrains.july.market.julymarket.services.OrdersService;
import com.geekbrains.july.market.julymarket.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {
    private UsersService usersService;
    private OrdersService ordersService;
    private Basket basket;

    @GetMapping("/create")
    public String createOrder(Principal principal, Model model) {
        User user = usersService.findByPhone(principal.getName()).get();
        model.addAttribute("user", user);
        return "order_info";
    }

    @PostMapping("/confirm")
    @ResponseBody
    public String confirmOrder(Principal principal, @RequestParam String address, @RequestParam String phone) {
        User user = usersService.findByPhone(principal.getName()).get();
        Order order = new Order(user, basket, phone, address);
        order = ordersService.saveOrder(order);
        return order.getId() + " " + order.getPrice();
    }
}
