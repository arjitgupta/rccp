package com.rest.connectionpool;

/**
 * Created with IntelliJ IDEA.
 * User: arjit.gupta
 * Date: 12/12/13
 * Time: 12:18 PM
 */
public class UnsupportedClientException extends Exception{
    public UnsupportedClientException(String exception){
                super(exception);
            }
}
