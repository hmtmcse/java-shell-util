package com.hmtmcse.shellutil.base;

import java.io.BufferedReader;
import java.io.IOException;
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

    public CommandResponse execute(CommandRequest commandRequest) {
        CommandResponse commandResponse = new CommandResponse();
        try {
            currentProcess = Runtime.getRuntime().exec(commandRequest.command, commandRequest.environment, commandRequest.commandHome);

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
        }
        return commandResponse;
    }
}
