package com.hmtmcse.shellutil.test;

import com.hmtmcse.shellutil.base.CommandRequest;
import com.hmtmcse.shellutil.base.CommandResponse;
import com.hmtmcse.shellutil.base.OSCommandExec;
import com.hmtmcse.shellutil.common.ShellUtilException;
import com.hmtmcse.shellutil.console.menu.ConsoleCommandMenu;
import org.apache.commons.cli.Option;

public class Main {

    public static void consoleMenuTest(String[] args) {
        try {
            ConsoleCommandMenu consoleCommandMenu = new ConsoleCommandMenu();
            Option option = consoleCommandMenu.option("m", true, "Multiple");
            option.setArgs(2);
            consoleCommandMenu
                    .addCommand("build", "Compile Source to Binary")
                    .addOption(option)
                    .addOption("c", "Config");


            consoleCommandMenu.addCommand("update", "Update by build")
                    .addOption(option);
            consoleCommandMenu.process(args);
        } catch (ShellUtilException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

        args = new String[]{"build", "-m" , "first", "second", "-c"};
        consoleMenuTest(args);


    }


    public static void execTest() {
        OSCommandExec osCommandExec = new OSCommandExec();
        CommandResponse commandResponse = osCommandExec.execute(CommandRequest.withCommand("which", "git").setPrintInConsole(true).setWaitUntilFinish(false));

        if (!commandResponse.isExecuted) {
            System.out.println(commandResponse.exceptionMessage);
        }

    }

}
