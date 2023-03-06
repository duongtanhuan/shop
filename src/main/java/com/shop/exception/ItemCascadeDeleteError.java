package com.shop.exception;

/**
 * ItemCascadeDeleteError.
 * */
public class ItemCascadeDeleteError extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  public ItemCascadeDeleteError(String msg) {
    super(msg);
  }
}
