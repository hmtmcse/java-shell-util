package com.hmtmcse.shellutil.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OSCommandExec {

    private Process currentProcess;

    public void stopExecution() {
        if (currentProcess != null) {
            currentProcess.destroy();
        }
    }

    public Process forceStopExecution() {
        if (currentProcess != null) {
            return currentProcess.destroyForcibly();
        }
        return null;
    }


    private String inputStreamToString(InputStream inputStream){
        StringBuilder stringBuffer = new StringBuilder();
       try{
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
           String line = "";
           while ((line = bufferedReader.readLine()) != null) {
               stringBuffer.append(line).append("\n");
           }
       } catch (IOException e) {
           stringBuffer.append("\n").append(":::::: IOException: ").append("\n");
           stringBuffer.append(e.getMessage()).append("\n");
           stringBuffer.append(":::::: End IOException").append("\n");
       }
       return stringBuffer.toString();
    }

    public CommandResponse execute(CommandRequest commandRequest) {
        CommandResponse commandResponse = new CommandResponse();
        try {
            currentProcess = Runtime.getRuntime().exec(commandRequest.command, commandRequest.getEnvironment(), commandRequest.commandHome);

            if (commandRequest.isWaitUntilFinish) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(currentProcess.getInputStream()));
                String line = "";
                StringBuffer stringBuffer = new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) {
                    if (commandRequest.isPrintInConsole) {
                        System.out.println(line);
                    }
                    if (commandRequest.cmdOutputLineCallBack != null) {
                        commandRequest.cmdOutputLineCallBack.eachLine(line, this);
                    } else {
                        stringBuffer.append(line).append("\n");
                    }
                }
                currentProcess.waitFor();
                commandResponse.exitCode = currentProcess.exitValue();
                commandResponse.commandOutput = stringBuffer.toString();
            }
        } catch (IOException | InterruptedException e) {
            commandResponse.isExecuted = false;
            commandResponse.exceptionMessage = e.getMessage();
            if (currentProcess != null){
                commandResponse.commandOutput = inputStreamToString(currentProcess.getErrorStream());
            }
        }
        return commandResponse;
    }
}
