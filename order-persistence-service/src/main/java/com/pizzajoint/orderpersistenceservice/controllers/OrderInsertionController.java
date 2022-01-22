package com.pizzajoint.orderpersistenceservice.controllers;

import com.pizzajoint.orderpersistenceservice.models.requests.PizzaOrderRequestPayload;
import com.pizzajoint.orderpersistenceservice.services.OrderInsertionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/order")
public class OrderInsertionController {

    Logger logger = LoggerFactory.getLogger(OrderInsertionController.class);

    @Autowired
    OrderInsertionService orderInsertionService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> addOrder(@Valid @RequestBody PizzaOrderRequestPayload pizzaOrder){
        logger.info("[addOrder] pizzaOrder:{}", pizzaOrder);
        orderInsertionService.addOrder(pizzaOrder);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
