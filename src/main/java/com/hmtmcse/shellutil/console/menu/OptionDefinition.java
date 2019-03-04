package com.hmtmcse.shellutil.console.menu;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.HashMap;

public class OptionDefinition {

    public String commandDescription;
    public CommandAction commandAction;
    private HashMap<String, OptionAttribute> options = new HashMap<>();
    private String lastIdentifier;
    private Options cliOptions = new Options();

    public OptionDefinition(CommandAction commandAction) {
        this.commandAction = commandAction;
    }

    public OptionDefinition setCommandDescription(String commandDescription) {
        this.commandDescription = commandDescription;
        return this;
    }

    public OptionDefinition addOption(String name, String identifier){
        this.lastIdentifier = identifier;
        options.put(identifier, new OptionAttribute(name, identifier));
        return this;
    }

    public OptionDefinition setDescription(String description) {
        this.options.get(lastIdentifier).description = description;
        return this;
    }

    public OptionDefinition setDefaultValue(String defaultValue) {
        this.options.get(lastIdentifier).defaultValue = defaultValue;
        return this;
    }

    public OptionDefinition required() {
        this.options.get(lastIdentifier).required();
        return this;
    }


    public OptionDefinition initOptions() {
        OptionAttribute optionAttribute;
        for (HashMap.Entry<String, OptionAttribute> entry : options.entrySet()){
            optionAttribute = entry.getValue();
            Option.Builder optionBuilder = Option.builder(optionAttribute.identifier);
            optionBuilder.required(optionAttribute.isRequired);
            optionBuilder.hasArg(optionAttribute.argument);
            if (optionAttribute.description != null){
                optionBuilder.desc(optionAttribute.description);
            }
            cliOptions.addOption(optionBuilder.build());
        }
        return this;
    }

    public Options getCliOptions() {
        return cliOptions;
    }

    public HashMap<String, OptionAttribute> getOptions() {
        return options;
    }
}
