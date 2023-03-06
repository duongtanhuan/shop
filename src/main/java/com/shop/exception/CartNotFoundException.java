package com.shop.exception;

/**
 * CartNotFoundException.
 * */
public class CartNotFoundException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  public CartNotFoundException(String msg) {
    super(msg);
  }
}
