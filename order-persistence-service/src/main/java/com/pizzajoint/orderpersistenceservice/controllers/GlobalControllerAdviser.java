package com.pizzajoint.orderpersistenceservice.controllers;

import com.pizzajoint.orderpersistenceservice.models.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalControllerAdviser extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalControllerAdviser.class);

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                 HttpHeaders headers,
                                                                 HttpStatus status,
                                                                 WebRequest request){
        logger.info("[handleMethodArgumentNotValid] Invalid user input");
        BindingResult bindingResult = exception.getBindingResult();
        String errorMessage= bindingResult.getFieldErrors().stream().map(e->e.getDefaultMessage()).reduce((s1,s2)->s1+" "+s2).orElse("");
        String uri = ((ServletWebRequest)request).getRequest().getRequestURI();
        logger.info("[handleMethodArgumentNotValid] errorMessage:{}",errorMessage);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),errorMessage,uri);
        return new ResponseEntity(errorResponse, headers, status);
    }
}
