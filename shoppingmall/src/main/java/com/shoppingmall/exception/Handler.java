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

    /**
     *
     * @param ex this is the message from CustomerNotFoundException class
     * @return it is returns the ResponseObject
     */
    @ExceptionHandler(CustomerNotFoundException.class)
        public ResponseEntity<ResponseObject> handle(CustomerNotFoundException ex)
        {
            ResponseObject response = new ResponseObject();
            response.setResponseCode("404");
            response.setReponseStatus(ex.getMessage());
            response.setStatus(HttpStatus.NOT_FOUND.toString());
            response.setStatusCode("404");
            response.setTimestamp(LocalDateTime.now());
            response.setMessage(ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    /**
     *
     * @param ex ex this is the message from ItemNotFoundException class
     * @return it is returns the ResponseObject
     */
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ResponseObject> handle(ItemNotFoundException ex)
    {
        ResponseObject response = new ResponseObject();
        response.setResponseCode("404");
        response.setReponseStatus(ex.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.toString());
        response.setStatusCode("404");
        response.setTimestamp(LocalDateTime.now());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param ex ex this is the message from OfferNotFoundException class
     * @return it is returns the ResponseObject
     */
    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<ResponseObject> handle(OfferNotFoundException ex)
    {
        ResponseObject response = new ResponseObject();
        response.setResponseCode("404");
        response.setReponseStatus(ex.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.toString());
        response.setStatusCode("404");
        response.setTimestamp(LocalDateTime.now());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
