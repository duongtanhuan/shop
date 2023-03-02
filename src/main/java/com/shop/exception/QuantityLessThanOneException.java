package com.shop.exception;

public class QuantityLessThanOneException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QuantityLessThanOneException(String msg) {
        super(msg);
    }
}
