package com.geekbrains.july.market.julymarket.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/security")
public class SecurityControllerTest {
    @GetMapping("/unsecured")
    public String usecuredPage() {
        return "unsecured";
    }

    @GetMapping("/")
    public String homePageSec() {
        return "home";
    }


    @GetMapping("/authenticated")
    public String authenticatedPage(Principal principal) {
//        Authentication a = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(Thread.currentThread().getName());
        String out = String.format("authenticated user: %s, password: %s", principal.getName(), "-");
        return out;
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
