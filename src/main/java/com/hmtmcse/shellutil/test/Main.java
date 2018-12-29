package com.hmtmcse.shellutil.test;

import com.hmtmcse.shellutil.base.CommandRequest;
import com.hmtmcse.shellutil.base.CommandResponse;
import com.hmtmcse.shellutil.base.OSCommandExec;

public class Main {

    public static void main(String[] args) {

        OSCommandExec osCommandExec = new OSCommandExec();
        CommandResponse commandResponse = osCommandExec.execute(CommandRequest.withCommand("which", "git").setPrintInConsole(true).setWaitUntilFinish(false));

        if (!commandResponse.isExecuted){
            System.out.println(commandResponse.exceptionMessage);
        }

    }

}
