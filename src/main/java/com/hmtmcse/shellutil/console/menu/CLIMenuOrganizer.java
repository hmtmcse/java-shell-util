package com.hmtmcse.shellutil.console.menu;

import com.hmtmcse.console.table.common.TableConstant;
import com.hmtmcse.console.table.data.Table;
import com.hmtmcse.console.table.data.TableRowData;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.util.LinkedHashMap;
import java.util.Map;


public class CLIMenuOrganizer {

   private LinkedHashMap<String, CLIMenuItem> cliMenuItemMap = new LinkedHashMap<>();

    public Options addCommand(String name, String description){
        cliMenuItemMap.put(name, new CLIMenuItem(name, description));
        return cliMenuItemMap.get(name).options;
    }

    public CLIMenuItem getMenuItem(String commandName){
        return cliMenuItemMap.get(commandName);
    }

    public CLIMenuOrganizer addCommand(String name, String description, Options options){
        cliMenuItemMap.put(name, new CLIMenuItem(name, description));
        cliMenuItemMap.get(name).options = options;
        return this;
    }


    public void printMenu(){
        Table table = new Table();
        table.addHeader("Command", TableConstant.LEFT_ALIGN, TableConstant.YALLOW);
        table.addHeader("Description", TableConstant.LEFT_ALIGN, TableConstant.YALLOW);
        TableRowData rowData;
        CLIMenuItem cliMenuItem;
        for (Map.Entry<String, CLIMenuItem> entry : cliMenuItemMap.entrySet()){
            cliMenuItem = entry.getValue();
            rowData = table.setRowData(cliMenuItem.commandName);
            rowData.add(cliMenuItem.commandDescription);
            table.addRow(rowData);
        }
        table.toTablePrint();
    }


    public Option option(String opt, boolean hasArg, String description){
        return new Option(opt, hasArg, description);
    }

    public void printHelp(String commandName){
        if (cliMenuItemMap.get(commandName) == null){
            System.err.println("Invalid Command " + commandName);
        }else{
            CLIMenuItem cliMenuItem = cliMenuItemMap.get(commandName);
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp(commandName, null, cliMenuItem.options, null, true);
        }
    }

}
