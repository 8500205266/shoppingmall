package com.shoppingmall.exceptions;


import com.shoppingmall.enumdatatype.ExceptionNamesWithCode;
import com.shoppingmall.exception.CustomerNotFoundException;
import com.shoppingmall.exception.Handler;
import com.shoppingmall.exception.ItemNotFoundException;
import com.shoppingmall.exception.OfferNotFoundException;
import com.shoppingmall.model.ResponseObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class Handlertest
{
    @InjectMocks
    private Handler handler;

    @Test
    public void handlerTestForCustomerNotFoundExceptin() throws CustomerNotFoundException
    {
        ExceptionNamesWithCode.Error customernotfound=ExceptionNamesWithCode.Error.CUSTOMERNOTFOUND;
        final CustomerNotFoundException exception = new CustomerNotFoundException(customernotfound);
        final ResponseEntity<ResponseObject> handle = handler.handle(exception);
        Assert.assertNotNull(handle);
    }

    @Test
    public void handlerTestForItemNotFoundExceptin()
    {
        ExceptionNamesWithCode.Error itemNotFound=ExceptionNamesWithCode.Error.ITEMNOTFOUND;
        final ItemNotFoundException exception = new ItemNotFoundException(itemNotFound);
        final ResponseEntity<ResponseObject> handle = handler.handle(exception);
        Assert.assertNotNull(handle);
    }

    @Test
    public void handlerTestForOfferNotFoundExceptin()
    {
        ExceptionNamesWithCode.Error offerIdNotFound=ExceptionNamesWithCode.Error.OFFERNOTFOUND;
        final OfferNotFoundException exception = new OfferNotFoundException(offerIdNotFound);
        final ResponseEntity<ResponseObject> handle = handler.handle(exception);
        Assert.assertNotNull(handle);
    }

}

