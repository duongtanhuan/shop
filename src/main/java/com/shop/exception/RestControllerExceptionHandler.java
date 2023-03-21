package com.shop.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * RestControllerExceptionHandler.
 * */
@RestControllerAdvice
public class RestControllerExceptionHandler {
  
  /**
   * handleInternalServerError.
   * */
  @ExceptionHandler(value = {ItemCascadeDeleteError.class, SystemErrorException.class,
          CartDetailCascadeDeleteError.class})
  public ResponseEntity<ErrorMessage> handleInternalServerError(RuntimeException ex,
                                                                WebRequest request) {
    ErrorMessage message = new ErrorMessage(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
    
    return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  /**
   * handleNotFound.
   * */
  @ExceptionHandler(value = {ItemNotFoundException.class, CustomerNotFoundException.class,
        CartNotFoundException.class, CartDetailNotFoundException.class})
  public ResponseEntity<ErrorMessage> handleNotFound(RuntimeException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
    
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }
  
  /**
   * handleBadRequest.
   * */
  @ExceptionHandler(value = {QuantityLessThanOneException.class, EmptyCartException.class,
          EmptyOrderDetailsException.class})
  public ResponseEntity<ErrorMessage> handleBadRequest(RuntimeException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
    
    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
  }
}
