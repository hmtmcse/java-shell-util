package com.hmtmcse.shellutil.base;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;


public class CommandRequest {

    public String[] command;
    public File commandHome = null;
    public Boolean isPrintInConsole = false;
    public Boolean isWaitUntilFinish = true;
    public Boolean isIncludeSystemEnvironment = true;
    public CmdOutputLineCallBack cmdOutputLineCallBack = null;
    private LinkedHashMap<String, String> environmentMap = new LinkedHashMap<String, String>();


    public CommandRequest setPrintInConsole(Boolean printInConsole) {
        isPrintInConsole = printInConsole;
        return this;
    }

    public CommandRequest setWaitUntilFinish(Boolean waitUntilFinish) {
        isWaitUntilFinish = waitUntilFinish;
        return this;
    }

    public CommandRequest setIncludeSystemEnvironment(Boolean includeSystemEnvironment) {
        isIncludeSystemEnvironment = includeSystemEnvironment;
        return this;
    }

    public CommandRequest setCommand(String[] command) {
        this.command = command;
        return this;
    }

    public CommandRequest addEnvironmentVariable(String key, String location) {
        environmentMap.put(key, location);
        return this;
    }

    public CommandRequest setCommandHome(File commandHome) {
        this.commandHome = commandHome;
        return this;
    }


    public CommandRequest setCommandHome(String commandHomeLocation) {
        this.commandHome = new File(commandHomeLocation);
        return this;
    }


    public String[] getEnvironment(){
        int count = 0;
        String[] envArray = null;
        if (isIncludeSystemEnvironment){
            LinkedHashMap<String, String> environment = new LinkedHashMap<String, String>(System.getenv());
            envArray = new String[environment.size() + environmentMap.size()];
            for (Map.Entry<String, String> entry : environment.entrySet()) {
                envArray[count++] = entry.getKey() + "=" + entry.getValue();
            }
        }else if (environmentMap.size() != 0){
            envArray = new String[environmentMap.size()];
        }

        if (environmentMap.size() != 0){
            for (Map.Entry<String, String> entry : environmentMap.entrySet()) {
                envArray[count++] = entry.getKey() + "=" + entry.getValue();
            }
        }
        return envArray;
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
