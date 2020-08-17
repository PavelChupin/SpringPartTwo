package com.geekbrains.july.market.julymarket.controllers;

import com.geekbrains.july.market.julymarket.basket.Basket;
import com.geekbrains.july.market.julymarket.services.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/basket")
@AllArgsConstructor
public class BasketController {
    private Basket basket;
    private ProductsService productsService;

    @GetMapping
    public String showCartPage(Model model) {
        model.addAttribute("basket", basket);
        return "basket";
    }

    @GetMapping("/add/{productId}")
    public void addProductToCartById(@PathVariable Long productId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        basket.add(productsService.findById(productId));
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/decrement/{productId}")
    public void decrementProductToCartById(@PathVariable Long productId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        basket.decrement(productsService.findById(productId));
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/remove/{productId}")
    public void removeProductFromCartById(@PathVariable Long productId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        basket.removeByProductId(productId);
        response.sendRedirect(request.getHeader("referer"));
    }
}
