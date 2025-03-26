package com.hertz.shoppingMall.utils.exception.custom;

public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException(String message) {
        super(message);
    }
}