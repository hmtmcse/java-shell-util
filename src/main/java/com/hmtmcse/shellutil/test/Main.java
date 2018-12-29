package com.hmtmcse.shellutil.test;

import com.hmtmcse.shellutil.base.CommandRequest;
import com.hmtmcse.shellutil.base.OSCommandExec;

public class Main {

    public static void main(String[] args) {

        OSCommandExec osCommandExec = new OSCommandExec();
        osCommandExec.execute(CommandRequest.withCommand("ping", "google.com").setPrintInConsole(true).setWaitUntilFinish(false));

    }

}
