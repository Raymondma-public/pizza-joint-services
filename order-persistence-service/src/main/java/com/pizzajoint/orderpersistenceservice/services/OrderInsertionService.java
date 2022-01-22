package com.pizzajoint.orderpersistenceservice.services;

import com.pizzajoint.orderpersistenceservice.daos.OrderRepository;
import com.pizzajoint.orderpersistenceservice.models.entities.PizzaOrder;
import com.pizzajoint.orderpersistenceservice.models.requests.PizzaOrderRequestPayload;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderInsertionService  {

    @Autowired
    OrderRepository orderRepository;

    public void addOrder(PizzaOrderRequestPayload pizzaOrderPayload) {
        PizzaOrder pizzaOrder = new PizzaOrder();
        BeanUtils.copyProperties(pizzaOrderPayload,pizzaOrder);
        orderRepository.save(pizzaOrder);
    }
}
