package com.pizzajoint.ordergenerationservice.controllers;

import com.pizzajoint.ordergenerationservice.exceptions.BadRequestException;
import com.pizzajoint.ordergenerationservice.services.OrderGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderGenerationController {

    Logger logger = LoggerFactory.getLogger(OrderGenerationController.class);
    @Autowired
    OrderGenerationService orderGenerationService;
    @PostMapping
    public ResponseEntity<Void> generateOrder(){
        logger.info("[generateOrder] start generating order");
        try {
            orderGenerationService.generateAndSend();
            return new ResponseEntity(HttpStatus.CREATED);

        } catch (BadRequestException e) {
            logger.error("[generateOrder] bad request while generating",e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            logger.error("[generateOrder] error while generating",e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
