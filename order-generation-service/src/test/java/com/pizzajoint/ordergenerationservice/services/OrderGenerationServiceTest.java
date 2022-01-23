package com.pizzajoint.ordergenerationservice.services;

import com.pizzajoint.ordergenerationservice.exceptions.BadRequestException;
import com.pizzajoint.ordergenerationservice.exceptions.InternalErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class OrderGenerationServiceTest {

    @Autowired
    OrderGenerationService orderGenerationService;

    @MockBean
    OrderPersistenceService orderPersistenceService;

    @Test
    void generateOrder_noError_noError() throws Exception {
        ResponseEntity<Void> mockResponse = new ResponseEntity(HttpStatus.CREATED);
        Mockito.doReturn(mockResponse).when(orderPersistenceService).addOrder(any());
        Assertions.assertDoesNotThrow(()->orderGenerationService.generateAndSend());
    }

    @Test
    void generateOrder_badRequest_badRequest() throws Exception {
        Mockito.doThrow(BadRequestException.class).when(orderPersistenceService).addOrder(any());
        Assertions.assertThrows(BadRequestException.class,()->orderGenerationService.generateAndSend());
    }

    @Test
    void generateOrder_internalErrorException_throwException() throws Exception {
        Mockito.doThrow(InternalErrorException.class).when(orderPersistenceService).addOrder(any());
        Assertions.assertThrows(InternalErrorException.class,()->orderGenerationService.generateAndSend());
    }

}
