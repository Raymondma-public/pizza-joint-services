package com.pizzajoint.ordergenerationservice.exceptions;

public class BadRequestException extends HttpException{

    public BadRequestException(String msg) {
        super(msg);
    }
}
