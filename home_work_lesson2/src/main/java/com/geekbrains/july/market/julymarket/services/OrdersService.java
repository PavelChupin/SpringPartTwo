package com.geekbrains.july.market.julymarket.services;


import com.geekbrains.july.market.julymarket.entities.Order;
import com.geekbrains.july.market.julymarket.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    private OrdersRepository ordersRepository;

    @Autowired
    public void setOrdersRepository(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Order saveOrder(Order order) {
        return ordersRepository.save(order);
    }
}
