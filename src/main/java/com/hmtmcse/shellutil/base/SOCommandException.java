package com.hmtmcse.shellutil.base;

/**
 * Created by Touhid Mia on 11/09/2014.
 */
public class SOCommandException extends Exception {

    public SOCommandException(){
        super("OS Command Exception Occurred!");
    }

    public SOCommandException(String message){
        super(message);
    }
}
