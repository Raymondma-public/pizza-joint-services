package com.pizzajoint.ordergenerationservice.services;

import com.pizzajoint.ordergenerationservice.exceptions.BadRequestException;
import com.pizzajoint.ordergenerationservice.exceptions.HttpException;
import com.pizzajoint.ordergenerationservice.exceptions.InternalErrorException;
import com.pizzajoint.ordergenerationservice.models.requests.PizzaOrderRequestPayload;
import feign.error.ErrorCodes;
import feign.error.ErrorHandling;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
@ErrorHandling(codeSpecific =
        {
                @ErrorCodes( codes = {400, 401,415}, generate = BadRequestException.class),
        },
        defaultException = InternalErrorException.class
)
@FeignClient(value = "order-persistence-service", url = "http://order-persistence-service/order")
public interface OrderPersistenceService {

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> addOrder(@Valid @RequestBody PizzaOrderRequestPayload pizzaOrder) throws HttpException;
}
