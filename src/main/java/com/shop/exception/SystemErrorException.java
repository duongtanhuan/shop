package com.shop.exception;

/**
 * SystemErrorException.
 * */
public class SystemErrorException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  public SystemErrorException(String msg) {
    super(msg);
  }
}
