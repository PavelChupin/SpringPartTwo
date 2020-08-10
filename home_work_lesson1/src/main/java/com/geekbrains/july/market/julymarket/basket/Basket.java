package com.geekbrains.july.market.julymarket.basket;

import com.geekbrains.july.market.julymarket.entities.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component("basket")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Basket {
    private List<Product> products;

    @PostConstruct
    public void init(){
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (!this.products.contains(product)){
            this.products.add(product);
        }
    }

    public void deleteProductById(Long id) {
        for (Product p : products) {
            if (p.getId() == id){
                products.remove(p);
                break;
            }
        }
    }

    public void clearProducts() {
        products.clear();
    }
}
