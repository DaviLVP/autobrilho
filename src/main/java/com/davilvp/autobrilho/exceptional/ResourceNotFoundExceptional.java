package com.davilvp.autobrilho.exceptional;

public class ResourceNotFoundExceptional extends RuntimeException {
    public ResourceNotFoundExceptional(String message) {
        super(message);
    }
}
