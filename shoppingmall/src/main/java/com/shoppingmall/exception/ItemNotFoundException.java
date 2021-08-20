package com.shoppingmall.exception;

public class ItemNotFoundException extends Exception
{
    /**
     *by passing the message into the base class constructor,
     * the base class will take care of the work of setting the message up correctly.
     * @param message
     */
    public ItemNotFoundException(String message)
    {
        super(message);
    }

}
