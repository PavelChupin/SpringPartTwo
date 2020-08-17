package com.geekbrains.july.market.julymarket.controllers;

import com.geekbrains.july.market.julymarket.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "users";
    }

    @GetMapping("profile/{id}")
    public String getUserById(Model model, @PathVariable Long id) {
        model.addAttribute("user", usersService.getUserById(id));
        return "user_profile";
    }

    @GetMapping("block/{id}")
    public String blockUnblockUser(@PathVariable Long id, @RequestParam(required = true) String block){
        usersService.blockUnblockUser(id, "on".equals(block));
        return "redirect:/users/profile/" + id;
    }

}
