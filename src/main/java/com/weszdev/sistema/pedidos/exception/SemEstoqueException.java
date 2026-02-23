package com.weszdev.sistema.pedidos.exception;

public class SemEstoqueException extends RuntimeException {
    public SemEstoqueException(String message) {
        super(message);
    }
}
