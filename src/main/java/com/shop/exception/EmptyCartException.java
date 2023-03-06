package com.shop.exception;

/**
 * EmptyCartException.
 * */
public class EmptyCartException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  public EmptyCartException(String msg) {
    super(msg);
  }
  
}
