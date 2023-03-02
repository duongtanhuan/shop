package com.shop.exception;

public class PaymentFailureException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PaymentFailureException(String msg) {
        super(msg);
    }
}
