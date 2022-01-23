package com.pizzajoint.ordergenerationservice.exceptions;

public class InternalErrorException extends HttpException{

    public InternalErrorException(String msg) {
        super(msg);
    }
}
