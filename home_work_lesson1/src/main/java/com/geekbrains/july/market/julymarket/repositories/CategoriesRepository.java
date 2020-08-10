package com.geekbrains.july.market.julymarket.repositories;

import com.geekbrains.july.market.julymarket.entities.Catergory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Catergory, Long>, JpaSpecificationExecutor<Catergory> {
}