package com.store.model.order;

// Обробка виключень, специфічних для узагальнень
public class InvalidProductException extends Exception {
    public InvalidProductException(String message) {
        super(message);
    }
}