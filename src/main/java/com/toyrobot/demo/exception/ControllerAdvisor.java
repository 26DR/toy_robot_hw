package com.toyrobot.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({ToyRobotException.class, ToyRobotNotFoundException.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        if (ex instanceof ToyRobotException) {
            return handleToyRobotExceptions(ex, HttpStatus.BAD_REQUEST);
        } else if (ex instanceof ToyRobotNotFoundException) {
            return handleToyRobotExceptions(ex, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> handleToyRobotExceptions(Exception ex, HttpStatus httpStatus) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, httpStatus);
    }
}

