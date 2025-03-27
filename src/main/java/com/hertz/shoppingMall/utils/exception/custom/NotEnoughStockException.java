package com.hertz.shoppingMall.utils.exception.custom;

public class NotEnoughStockException extends RuntimeException{
    public NotEnoughStockException(String message){
        super(message);
    }
}
