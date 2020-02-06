package com.hmtmcse.shellutil.base;

import com.hmtmcse.shellutil.print.ConsolePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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

    private void printOutput(CommandRequest commandRequest, InputStream inputStream, StringBuffer stringBuffer, List<String> outLines) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            if (commandRequest.isPrintInConsole) {
                ConsolePrinter.printLine(line);
            }
            if (commandRequest.cmdOutputLineCallBack != null) {
                commandRequest.cmdOutputLineCallBack.eachLine(line, this);
            }
            stringBuffer.append(line).append(System.lineSeparator());
            outLines.add(line);
        }
    }

    public CommandResponse execute(CommandRequest commandRequest) {
        CommandResponse commandResponse = new CommandResponse();
        try {
            if (commandRequest.isPrintCommands){
                ConsolePrinter.printLine(commandRequest.command);
            }
            currentProcess = Runtime.getRuntime().exec(commandRequest.command, commandRequest.getEnvironment(), commandRequest.commandHome);
            if (commandRequest.isWaitUntilFinish) {

                StringBuffer stringBuffer = new StringBuffer();
                printOutput(commandRequest, currentProcess.getInputStream(), stringBuffer, commandResponse.commandOutputLine);

                StringBuffer errorBuffer = new StringBuffer();
                printOutput(commandRequest, currentProcess.getErrorStream(), errorBuffer, commandResponse.errorOutputLine);

                currentProcess.waitFor();
                commandResponse.exitCode = currentProcess.exitValue();
                commandResponse.commandOutput = stringBuffer.toString();
                commandResponse.errorOutput = errorBuffer.toString();
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
