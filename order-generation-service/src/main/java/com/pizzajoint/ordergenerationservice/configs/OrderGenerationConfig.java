package com.pizzajoint.ordergenerationservice.configs;

import com.pizzajoint.ordergenerationservice.exceptions.HttpException;
import com.pizzajoint.ordergenerationservice.services.OrderGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class OrderGenerationConfig {
    Logger logger = LoggerFactory.getLogger(OrderGenerationConfig.class);
    @Autowired
    OrderGenerationService orderGenerationService;

    @EventListener(ApplicationReadyEvent.class)
    public void generateOrderOnStart(){
        logger.info("[generateOrderOnStart] start generation");
        try {
            orderGenerationService.generateAndSend();
        } catch (HttpException e) {
            logger.error("[generateOrderOnStart] Error occur while generating on start",e);
        }
    }
}
