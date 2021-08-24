package com.shoppingmall.enumdatatype;

public class ExceptionNamesWithCode
{
public enum Error
{
    CUSTOMERNOTFOUND(404, "Customer Id is Not Found!!"),
    ITEMNOTFOUND(404, "This Item Id is Not Found!!"),
    OFFERNOTFOUND(404,"This Offer Id is Not Found!!");

    private final int code;
    private final String description;

    private Error(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
}