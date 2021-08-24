package com.shoppingmall.exceptions;

import com.shoppingmall.enumdatatype.ExceptionNamesWithCode;
import com.shoppingmall.exception.CustomerNotFoundException;
import com.shoppingmall.exception.ItemNotFoundException;
import com.shoppingmall.exception.OfferNotFoundException;
import org.junit.Test;

public class CustomExceptionsTest
{


    @Test(expected = CustomerNotFoundException.class)
    public void customerNotFoundExceptionTest() throws CustomerNotFoundException
    {
        ExceptionNamesWithCode.Error customernotfound=ExceptionNamesWithCode.Error.CUSTOMERNOTFOUND;
        throw new CustomerNotFoundException(customernotfound);
    }

    @Test(expected = ItemNotFoundException.class)
    public void itemNotFoundExceptionTest() throws ItemNotFoundException
    {
        ExceptionNamesWithCode.Error itemNotFound=ExceptionNamesWithCode.Error.ITEMNOTFOUND;
        throw new ItemNotFoundException(itemNotFound);
    }


    @Test(expected = OfferNotFoundException.class)
    public void offerNotFoundExceptionTest() throws OfferNotFoundException
    {
        ExceptionNamesWithCode.Error offerIdNotFound=ExceptionNamesWithCode.Error.OFFERNOTFOUND;
        throw new OfferNotFoundException(offerIdNotFound);
    }
}
