package com.shoppingmall.exception;

import com.shoppingmall.enumdatatype.ExceptionNamesWithCode;

public class ItemNotFoundException extends Exception
{
    /**
     *by passing the message into the base class constructor,
     * the base class will take care of the work of setting the message up correctly.
     * @param message
     */
    public ItemNotFoundException(ExceptionNamesWithCode.Error message)
    {
        super(String.valueOf(message));
    }

}
