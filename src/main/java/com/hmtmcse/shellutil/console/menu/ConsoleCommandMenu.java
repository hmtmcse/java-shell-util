package com.hmtmcse.shellutil.console.menu;

import org.apache.commons.cli.*;

import java.util.Arrays;

public class ConsoleCommandMenu extends CLIMenuOrganizer{

    private void execute(String[] args){
        String command = args[0];
        Arrays.copyOfRange(args, 1, args.length);

        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = commandLineParser.parse( new Options(), args);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //***Interrogation Stage***
        //hasOptions checks if option is present or not
        if(cmd.hasOption("a")) {
            System.out.println("Sum of the numbers: ");
        } else if(cmd.hasOption("m")) {
            System.out.println("Multiplication of the numbers: ");
        }



    }

    public void process(String[] args){
        if (args.length == 0){
            printMenu();
        }else if (args.length == 1){
            printHelp(args[0]);
        }
    }

}
