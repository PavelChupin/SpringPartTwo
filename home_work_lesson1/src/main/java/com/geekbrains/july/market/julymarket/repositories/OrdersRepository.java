package com.geekbrains.july.market.julymarket.repositories;


import com.geekbrains.july.market.julymarket.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<Order, Long> {
}
