package com.example.library.domian.expection;

public class BusinessRuleException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BusinessRuleException(String message){
        super(message);
    }
}
