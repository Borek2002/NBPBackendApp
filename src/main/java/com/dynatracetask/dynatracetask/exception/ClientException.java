package com.dynatracetask.dynatracetask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClientException extends RuntimeException{
    public ClientException(String message){
        super(message);
    }
}
