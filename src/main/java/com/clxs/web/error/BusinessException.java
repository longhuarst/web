package com.clxs.web.error;

//业务异常
public class BusinessException extends RuntimeException{
    public BusinessException(){ }

    public BusinessException(String message){
        super(message);
    }

}
