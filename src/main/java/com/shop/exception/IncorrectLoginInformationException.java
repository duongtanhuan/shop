package com.shop.exception;

/**
 * IncorrectLoginInformationException.
 * */
public class IncorrectLoginInformationException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  public IncorrectLoginInformationException(String msg) {
    super(msg);
  }
}
