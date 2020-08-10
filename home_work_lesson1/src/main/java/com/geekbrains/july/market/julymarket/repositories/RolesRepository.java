package com.geekbrains.july.market.julymarket.repositories;


import com.geekbrains.july.market.julymarket.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
    Role findOneByName(String name);
}
