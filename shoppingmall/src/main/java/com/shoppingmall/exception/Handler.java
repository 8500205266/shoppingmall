package com.shoppingmall.exception;

import com.shoppingmall.model.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class Handler extends RuntimeException
{
        @ExceptionHandler(BadException.class)
    public ResponseEntity<ResponseObject> handle(BadException ex)
        {
            ResponseObject response = new ResponseObject();
            response.setResponseCode("400");
            response.setReponseStatus("Bad Request");
            response.setStatus("It is Bad Request");
            response.setStatusCode("422");
            response.setTimestamp(LocalDateTime.now());
            response.setMessage(ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

}
