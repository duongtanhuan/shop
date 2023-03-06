package com.shop.exception;

/**
 * ItemCascadeDeleteError.
 * */
public class ItemNotFoundException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  public ItemNotFoundException(String msg) {
    super(msg);
  }
}
