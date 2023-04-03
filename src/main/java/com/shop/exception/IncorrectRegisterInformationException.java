package com.shop.exception;

/**
 * IncorrectRegisterInformationException.
 * */
public class IncorrectRegisterInformationException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  public IncorrectRegisterInformationException(String msg) {
    super(msg);
  }
}
