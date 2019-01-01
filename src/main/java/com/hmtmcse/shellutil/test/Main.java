package com.hmtmcse.shellutil.test;

import com.hmtmcse.shellutil.base.CommandRequest;
import com.hmtmcse.shellutil.base.CommandResponse;
import com.hmtmcse.shellutil.base.OSCommandExec;
import com.hmtmcse.shellutil.console.menu.ConsoleCommandMenu;

public class Main {

    public static void consoleMenuTest(String[] args){
        ConsoleCommandMenu consoleCommandMenu = new ConsoleCommandMenu();
        consoleCommandMenu
                .addCommand("build", "Compile Source to Binary")
                .addOption("c", "Config");
        consoleCommandMenu.process(args);

    }


    public static void main(String[] args) {

        args = new String[]{"build"};
        consoleMenuTest(args);


    }


    public static void execTest(){
        OSCommandExec osCommandExec = new OSCommandExec();
        CommandResponse commandResponse = osCommandExec.execute(CommandRequest.withCommand("which", "git").setPrintInConsole(true).setWaitUntilFinish(false));

        if (!commandResponse.isExecuted){
            System.out.println(commandResponse.exceptionMessage);
        }

    }

}
