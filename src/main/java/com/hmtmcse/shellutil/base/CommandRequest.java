package com.hmtmcse.shellutil.base;

import java.io.File;


public class CommandRequest {

    public String[] command;
    public String[] environment = null;
    public File commandHome = null;
    public Boolean isPrintInConsole = false;
    public Boolean isWaitUntilFinish = true;
    public CmdOutputLineCallBack cmdOutputLineCallBack = null;


    public CommandRequest setPrintInConsole(Boolean printInConsole) {
        isPrintInConsole = printInConsole;
        return this;
    }

    public CommandRequest setWaitUntilFinish(Boolean waitUntilFinish) {
        isWaitUntilFinish = waitUntilFinish;
        return this;
    }

    public CommandRequest setCommand(String[] command) {
        this.command = command;
        return this;
    }

    public static CommandRequest getInstance(){
        return new CommandRequest();
    }

    public static CommandRequest withCommand(String command){
        return new CommandRequest();
    }

    public static CommandRequest withCommand(String ...command){
        return new CommandRequest().setCommand(command);
    }
}
