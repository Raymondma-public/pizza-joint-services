package com.pizzajoint.ordergenerationservice.services;

import com.pizzajoint.ordergenerationservice.exceptions.HttpException;
import com.pizzajoint.ordergenerationservice.models.requests.PizzaOrderRequestPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderGenerationService {
    @Autowired
    OrderPersistenceService orderPersistenceService;

    @Value("${pizza-order.quantity.lower-bound}")
    Integer quantityLowerBound;

    @Value("${pizza-order.quantity.upper-bound}")
    Integer quantityUpperBound;

    @Value("${pizza-order.price.lower-bound}")
    Integer priceLowerBound;

    @Value("${pizza-order.price.upper-bound}")
    Integer priceUpperBound;

    PizzaOrderRequestPayload generateRandomOrder() {
        Random random=new Random();
        PizzaOrderRequestPayload pizzaOrderRequestPayload = new PizzaOrderRequestPayload();
        char c = (char)(random.nextInt(26) + 'a');
        pizzaOrderRequestPayload.setName("Pizza-"+c);
        pizzaOrderRequestPayload.setQuantity(random.nextInt(quantityUpperBound-quantityLowerBound)+quantityLowerBound);
        pizzaOrderRequestPayload.setPrice(Math.round((random.nextDouble()*(priceUpperBound-priceLowerBound)+priceLowerBound)*100.0)/100.0);
        return pizzaOrderRequestPayload;
    }

    public void generateAndSend() throws HttpException {
        PizzaOrderRequestPayload pizzaOrderRequestPayload = generateRandomOrder();
        orderPersistenceService.addOrder(pizzaOrderRequestPayload);
    }

}
