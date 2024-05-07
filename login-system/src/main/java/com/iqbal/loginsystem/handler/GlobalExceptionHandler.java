package com.iqbal.loginsystem.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException e){
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.valueOf(e.getStatusCode().value()).getReasonPhrase())
                .message(e.getReason())
                .build();

        return ResponseEntity.status(e.getStatusCode()).body(response);
    }

}
