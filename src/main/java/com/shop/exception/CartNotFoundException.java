package com.shop.exception;

public class CartNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CartNotFoundException(String msg) {
        super(msg);
    }
}
