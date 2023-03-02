package com.shop.exception;

public class CartDuplicateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CartDuplicateException(String msg) {
        super(msg);
    }
}
