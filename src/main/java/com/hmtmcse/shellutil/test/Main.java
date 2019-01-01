package com.hmtmcse.shellutil.test;

import com.hmtmcse.shellutil.base.CommandRequest;
import com.hmtmcse.shellutil.base.CommandResponse;
import com.hmtmcse.shellutil.base.OSCommandExec;
import com.hmtmcse.shellutil.console.menu.ConsoleCommandMenu;

public class Main {

    public static void consoleMenuTest(){
        ConsoleCommandMenu consoleCommandMenu = new ConsoleCommandMenu();
        consoleCommandMenu.addCommand("build", "Compile Source to Binary");
    }


    public static void main(String[] args) {

        OSCommandExec osCommandExec = new OSCommandExec();
        CommandResponse commandResponse = osCommandExec.execute(CommandRequest.withCommand("which", "git").setPrintInConsole(true).setWaitUntilFinish(false));

        if (!commandResponse.isExecuted){
            System.out.println(commandResponse.exceptionMessage);
        }

    }

}
