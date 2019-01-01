package com.hmtmcse.shellutil.common;

/**
 * Created by Touhid Mia on 11/09/2014.
 */
public class ShellUtilException extends Exception {

    public ShellUtilException(){
        super("OS Command Exception Occurred!");
    }

    public ShellUtilException(String message){
        super(message);
    }
}
