package com.ma.apigateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CJwtTokenIncorrectStructureException extends RuntimeException{
    public CJwtTokenIncorrectStructureException(String message){
        super(message);
    }
}
