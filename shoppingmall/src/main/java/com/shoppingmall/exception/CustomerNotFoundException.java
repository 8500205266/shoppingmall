package com.shoppingmall.exception;


public class CustomerNotFoundException extends Exception
{
    /**
     *
     * @param message
     */
    public CustomerNotFoundException(String message)
    {
        super(message);
    }
}
