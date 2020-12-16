package com.apirest.apiinformatorio.controller;

import com.apirest.apiinformatorio.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException exception){
        return new ResponseEntity<>("ID de usuario invalido.", HttpStatus.NOT_FOUND);
    }

}
