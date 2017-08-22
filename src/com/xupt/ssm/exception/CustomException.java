package com.xupt.ssm.exception;

/**
 * 系统自定义异常类，针对预期的异常，需要在程序中抛出此类异常
 * Created by 梁峻磊 on 2017/8/22.
 */
public class CustomException extends Exception {
    //异常信息
    public String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
