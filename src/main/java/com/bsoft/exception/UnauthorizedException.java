package com.bsoft.exception;

/**
 * 为了实现我自己能够手动抛出异常
 */
public class UnauthorizedException extends  RuntimeException  {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }

}
