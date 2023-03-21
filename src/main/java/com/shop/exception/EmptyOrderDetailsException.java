package com.shop.exception;

/**
 * EmptyOrderDetailsException.
 * */
public class EmptyOrderDetailsException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  public EmptyOrderDetailsException(String msg) {
    super(msg);
  }
}
