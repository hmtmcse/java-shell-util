package com.hmtmcse.shellutil.test;

import com.hmtmcse.shellutil.common.ShellUtilException;
import com.hmtmcse.shellutil.console.menu.CommandAction;
import com.hmtmcse.shellutil.console.menu.CommandProcessor;
import com.hmtmcse.shellutil.console.menu.OptionDefinition;
import com.hmtmcse.shellutil.console.menu.OptionValues;
import com.hmtmcse.shellutil.print.ConsolePrinter;

public class ConsoleMenuTest {

    public static OptionDefinition build(){
        OptionDefinition optionDefinition = new OptionDefinition(new CommandAction() {
            @Override
            public void process(OptionValues optionValues) throws ShellUtilException {
                ConsolePrinter.printLine("I am in Action Processor");
                ConsolePrinter.successPrint("Branch: " + optionValues.valueAsString("branch"));
                ConsolePrinter.successPrint("Type: " + optionValues.valueAsString("type"));
            }
        });
        optionDefinition.setCommandDescription("Build Binary from Source");

        optionDefinition.addOption("branch", "b");
        optionDefinition.required().setDescription("VCS Branch Name");

        optionDefinition.addOption("type", "t");
        optionDefinition.setDescription("Build Type: optimize / fresh").setDefaultValue("optimize");

        return optionDefinition;
    }

    public static OptionDefinition update(){
        OptionDefinition optionDefinition = new OptionDefinition(new CommandAction() {
            @Override
            public void process(OptionValues optionValues) throws ShellUtilException {

            }
        });
        optionDefinition.setCommandDescription("Update Instance with new Binary");

        optionDefinition.addOption("version", "v");
        optionDefinition.required().setDescription("Binary Version Number.");

        optionDefinition.addOption("instance", "i");
        optionDefinition.setDescription("Name of the Instance.").required();

        return optionDefinition;
    }


    public static void init(String[] args) {
        CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.addCommand("build", build());
        commandProcessor.addCommand("update", update());

        try {
            commandProcessor.processCommands(args);
        } catch (ShellUtilException e) {
            ConsolePrinter.errorPrint(e.getMessage());
            commandProcessor.printMenu();
        }
    }

    public static void main(String[] args) {

        ConsolePrinter.debugPrint("Empty Input Test:");
        init(new String[]{});


        ConsolePrinter.printEmptyLine();
        ConsolePrinter.debugPrint("Sent Only Command:");
        init(new String[]{"build"});

        ConsolePrinter.printEmptyLine();
        ConsolePrinter.debugPrint("Build Help:");
        init(new String[]{"build", "-help"});


        ConsolePrinter.printEmptyLine();
        ConsolePrinter.debugPrint("Build With Correct Options:");
        init(new String[]{"build", "-t", "fresh", "-b", "master"});


        ConsolePrinter.printEmptyLine();
        ConsolePrinter.debugPrint("Build Required option Missing:");
        init(new String[]{"build", "-t", "fresh"});

    }

}
