package com.hmtmcse.shellutil.console.menu;

import com.hmtmcse.shellutil.common.ShellUtilException;
import org.apache.commons.cli.*;

import java.util.Arrays;

public class ConsoleCommandMenu extends CLIMenuOrganizer {

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
            if (commandLine.hasOption("a")) {
                System.out.println("Sum of the numbers: ");
            } else if (commandLine.hasOption("m")) {
                System.out.println("Multiplication of the numbers: ");
            }
        } catch (ParseException e) {
            throw new ShellUtilException(e.getMessage());
        }
    }

    public void process(String[] args) throws ShellUtilException {
        if (args.length == 0) {
            printMenu();
        } else if (args.length == 1) {
            printHelp(args[0]);
        } else {
            execute(args);
        }
    }

}
