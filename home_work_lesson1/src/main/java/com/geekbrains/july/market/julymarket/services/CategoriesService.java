package com.geekbrains.july.market.julymarket.services;

import com.geekbrains.july.market.julymarket.entities.Catergory;
import com.geekbrains.july.market.julymarket.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    private CategoriesRepository categoriesRepository;

    @Autowired
    public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Catergory> findAll() {
        return categoriesRepository.findAll();
    }

}
