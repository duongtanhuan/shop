package com.shop.exception;

public class CartDetailNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CartDetailNotFoundException(String msg) {
        super(msg);
    }
}
