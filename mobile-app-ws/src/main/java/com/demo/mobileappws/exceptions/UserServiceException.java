package com.demo.mobileappws.exceptions;

public class UserServiceException extends RuntimeException{

    private static final long serialVersionUID = 5776681206288518465L;

    public UserServiceException(String message)
    {
        super(message);
    }
}
