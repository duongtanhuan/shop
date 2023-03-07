package com.shop.exception;

public class CartDetailCascadeDeleteError extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  public CartDetailCascadeDeleteError(String msg) {
    super(msg);
  }
}
