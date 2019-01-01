package com.hmtmcse.shellutil.console.menu;

public class ConsoleCommandMenu extends CLIMenuOrganizer{

    public void process(String[] args){

        if (args.length == 0){
            printMenu();
        }


    }

}
