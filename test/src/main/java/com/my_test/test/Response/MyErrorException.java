package com.my_test.test.Response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class MyErrorException extends RuntimeException {
    public MyErrorException(String message) {
        super(message);
    }
}
