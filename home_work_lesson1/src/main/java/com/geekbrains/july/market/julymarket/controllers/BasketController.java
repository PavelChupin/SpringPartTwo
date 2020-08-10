package com.geekbrains.july.market.julymarket.controllers;

import com.geekbrains.july.market.julymarket.basket.Basket;
import com.geekbrains.july.market.julymarket.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/basket")
public class BasketController {
    private Basket basket;
    private ProductsService productsService;

    @Autowired
    public BasketController(Basket basket, ProductsService productsService) {
        this.basket = basket;
        this.productsService = productsService;
    }

    @GetMapping
    public String showAllProductInBasket(Model model) {
        model.addAttribute("products", basket.getProducts());
        return "basket";
    }

    @GetMapping("/add/{id}")
    public String addProductToBasket(@PathVariable Long id, Model model) {
        basket.addProduct(productsService.findById(id));
        return "redirect:/products/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductFromBasket(@PathVariable Long id, Model model) {
        basket.deleteProductById(id);
        return "redirect:/basket/";
    }

    @GetMapping("/clear")
    public String clearProductFromBasket(Model model) {
        basket.clearProducts();
        return "redirect:/basket/";
    }
}
