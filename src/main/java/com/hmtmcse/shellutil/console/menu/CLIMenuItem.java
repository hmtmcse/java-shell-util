package com.hmtmcse.shellutil.console.menu;

import org.apache.commons.cli.Options;

public class CLIMenuItem {

    public String commandName;
    public String commandDescription = null;
    public Options options = null;


    public CLIMenuItem(String commandName, String commandDescription) {
        this.commandName = commandName;
        this.commandDescription = commandDescription;
    }

    public CLIMenuItem setCommandName(String commandName) {
        this.commandName = commandName;
        return this;
    }

    public CLIMenuItem setCommandDescription(String commandDescription) {
        this.commandDescription = commandDescription;
        return this;
    }

    public CLIMenuItem setOptions(Options options) {
        this.options = options;
        return this;
    }
}
