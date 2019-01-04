package com.hmtmcse.shellutil.console.menu;

import com.hmtmcse.shellutil.common.ShellUtilException;
import com.hmtmcse.shellutil.print.ConsolePrinter;
import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class ConsoleCommandMenu extends CLIMenuOrganizer {

    private String getOptionKey(Option option){
        if (option.getArgName() != null){
            return option.getArgName();
        }else{
            return option.getOpt();
        }
    }

    private LinkedHashMap<String, Object> getCommandOptionsToMap(CommandLine commandLine, Options optionDefinition) {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        for (Option option : optionDefinition.getOptions()) {
            if (option.getArgs() == -1) {
                if (commandLine.hasOption(option.getOpt())) {
                    linkedHashMap.put(getOptionKey(option), true);
                } else {
                    linkedHashMap.put(getOptionKey(option), false);
                }
            } else {
                if (option.getArgs() == 1) {
                    if (commandLine.hasOption(option.getOpt())) {
                        linkedHashMap.put(getOptionKey(option), commandLine.getOptionValue(option.getOpt()));
                    } else {
                        linkedHashMap.put(getOptionKey(option), null);
                    }
                } else {
                    String optionKey = getOptionKey(option);
                    for (int i = 1; i <= option.getArgs(); i++) {
                        linkedHashMap.put(optionKey + "Param" + i, null);
                    }
                    if (commandLine.hasOption(option.getOpt())) {
                        int i = 1;
                        for(Object object: commandLine.getOptionValues(option.getOpt())){
                            linkedHashMap.put(optionKey + "Param" + i, object);
                            i++;
                        }
                    }
                }
            }
        }
        return linkedHashMap;
    }

    private void execute(String[] args) throws ShellUtilException {
        try {
            CommandLineParser commandLineParser = new DefaultParser();
            String commandName = args[0];
            String[] options = Arrays.copyOfRange(args, 1, args.length);
            CLIMenuItem cliMenuItem = getMenuItem(commandName);
            if (cliMenuItem == null || cliMenuItem.options == null) {
                throw new ShellUtilException("Invalid Command Options Definition.");
            }
            Options optionDefinition = cliMenuItem.options;
            CommandLine commandLine = commandLineParser.parse(optionDefinition, options);
            LinkedHashMap<String, Object> params = getCommandOptionsToMap(commandLine, optionDefinition);
            if (cliMenuItem.cliMenuItemProcessor != null){
                cliMenuItem.cliMenuItemProcessor.process(params);
            }
        } catch (ParseException e) {
            throw new ShellUtilException(e.getMessage());
        }
    }


    public void process(String[] args) throws ShellUtilException {
        if (args.length == 0) {
            printMenu();
        } else if (args.length == 1) {
            ConsolePrinter.printEmptyLine();
            printHelp(args[0]);
        } else {
            execute(args);
        }
    }

}
