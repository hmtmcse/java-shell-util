package com.hmtmcse.shellutil.console.menu;

import com.hmtmcse.console.table.common.TableConstant;
import com.hmtmcse.console.table.data.Table;
import com.hmtmcse.console.table.data.TableRowData;
import com.hmtmcse.shellutil.common.ShellUtilException;
import com.hmtmcse.shellutil.print.ConsolePrinter;
import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {

    private HashMap<String, OptionDefinition> commands = new HashMap<>();

    public void addCommand(String command, OptionDefinition optionDefinition) {
        commands.put(command, optionDefinition);
    }


    public void processCommands(String[] args) throws ShellUtilException {
        try {
            if (args.length == 0) {
                throw new ShellUtilException("You may forgot to set command");
            }
            String commandName = args[0];
            OptionDefinition optionDefinition;
            if (commands.get(commandName) == null) {
                throw new ShellUtilException("Command Not defined.");
            } else {
                optionDefinition = commands.get(commandName);
            }
            optionDefinition.initOptions();
            String[] options = Arrays.copyOfRange(args, 1, args.length);

            if (args.length == 2 && args[1].equals("-help")){
                printHelp(commandName);
                return;
            }

            CommandLineParser commandLineParser = new DefaultParser();
            CommandLine commandLine = commandLineParser.parse(optionDefinition.getCliOptions(), options);
            OptionValues optionValues = processOptions(commandLine, optionDefinition);

            if (optionDefinition.commandAction == null){
                throw new ShellUtilException("Command Action Processor Not Available");
            }
            optionDefinition.commandAction.process(optionValues);

        } catch (ParseException e) {
            throw new ShellUtilException(e.getMessage());
        }
    }


    private void printHelp(String commandName){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( commandName, commands.get(commandName).getCliOptions());
    }


    private OptionValues processOptions(CommandLine commandLine, OptionDefinition optionDefinition){
        if (optionDefinition.getCliOptions().getOptions().size() == 0 || commandLine == null){
            return null;
        }

        OptionValues optionValues = OptionValues.init();
        String identifier;
        for (Option option : optionDefinition.getCliOptions().getOptions()) {
            identifier = option.getOpt();
            optionValues.setValue(optionDefinition.getOptions().get(identifier), commandLine.getOptionValue(identifier));
        }
        return optionValues;
    }

    public void printMenu() {
        Table table = new Table();
        table.addHeader("Command", TableConstant.LEFT_ALIGN, TableConstant.YALLOW);
        table.addHeader("Description", TableConstant.LEFT_ALIGN, TableConstant.YALLOW);
        TableRowData rowData;
        OptionDefinition optionDefinition;
        for (Map.Entry<String, OptionDefinition> entry : commands.entrySet()) {
            optionDefinition = entry.getValue();
            rowData = table.setRowData(entry.getKey(), TableConstant.LEFT_ALIGN, TableConstant.BLUE);
            rowData.add(optionDefinition.commandDescription);
            table.addRow(rowData);
        }

        ConsolePrinter.printEmptyLine();
        ConsolePrinter.successPrint("For help please run: command -help");
        table.toTablePrint();
    }

}
