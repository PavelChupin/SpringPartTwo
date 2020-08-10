package com.geekbrains.july.market.julymarket.controllers;


import com.geekbrains.july.market.julymarket.basket.Basket;
import com.geekbrains.july.market.julymarket.entities.Catergory;
import com.geekbrains.july.market.julymarket.entities.Product;
import com.geekbrains.july.market.julymarket.services.CategoriesService;
import com.geekbrains.july.market.julymarket.services.ProductsService;
import com.geekbrains.july.market.julymarket.utils.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;
    private CategoriesService categoriesService;

    @Autowired
    public ProductsController(ProductsService productsService, CategoriesService categoriesService/*, Basket basket*/) {
        this.productsService = productsService;
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public String showAll(Model model, @RequestParam Map<String, String> requestParams) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));
        List<Catergory> categories = categoriesService.findAll();
        ProductFilter productFilter = new ProductFilter(requestParams, categories);
        Page<Product> products = productsService.findAll(productFilter.getSpec(), pageNumber);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("filterDef", productFilter.getFilterDefinition().toString());
        return "all_products";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_product_form";
    }

    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute Product product) {
        productsService.saveOrUpdate(product);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productsService.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product product) {
        productsService.saveOrUpdate(product);
        return "redirect:/products/";
    }
}