package com.ma.apigateway.exception;

import javax.naming.AuthenticationException;

public class CJwtTokenMissingException extends AuthenticationException {

    public CJwtTokenMissingException(String message){
        super(message);
    }
}
