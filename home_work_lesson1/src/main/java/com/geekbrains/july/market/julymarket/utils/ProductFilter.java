package com.geekbrains.july.market.julymarket.utils;

import com.geekbrains.july.market.julymarket.entities.Catergory;
import com.geekbrains.july.market.julymarket.entities.Product;
import com.geekbrains.july.market.julymarket.repositories.specifications.ProductSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;
    private StringBuilder filterDefinition;

    public ProductFilter(Map<String, String> map, List<Catergory> categories) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("min_price") && !map.get("min_price").isEmpty()) {
            int minPrice = Integer.parseInt(map.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
            filterDefinition.append("&min_price=").append(minPrice);
        }
        if (map.containsKey("max_price") && !map.get("max_price").isEmpty()) {
            int maxPrice = Integer.parseInt(map.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
            filterDefinition.append("&max_price=").append(maxPrice);
        }
        if (map.containsKey("product_name") && !map.get("product_name").isEmpty()) {
            String productName = map.get("product_name");
            spec = spec.and(ProductSpecifications.findProductByPartTitle(productName));
            filterDefinition.append("&product_name=").append(productName);
        }


        //Обрабатываем фильтр категорий
        Specification categorySpec = null;
        //Сосавляем перечень категорий из входных параметров
        for (Catergory c : categories) {
            if (map.get(c.getTitle()) != null && "on".equals(map.get(c.getTitle()))) {
                if (categorySpec == null) {
                    categorySpec = ProductSpecifications.findProductByCategory(c);
                } else {
                    categorySpec = categorySpec.or(ProductSpecifications.findProductByCategory(c));
                }
            }
        }
        if (categorySpec != null) {
            spec = spec.and(categorySpec);
        }
    }
}
