package com.hmtmcse.shellutil.console.menu;

public class OptionAttribute {

    public String name;
    public String identifier;
    public String description;
    public String defaultValue = null;
    public Boolean isRequired = false;
    public Boolean argument = true;
    public String value;

    public OptionAttribute(String name, String identifier) {
        this.name = name;
        this.identifier = identifier;
    }

    public OptionAttribute setName(String name) {
        this.name = name;
        return this;
    }

    public OptionAttribute setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public OptionAttribute setDescription(String description) {
        this.description = description;
        return this;
    }

    public OptionAttribute setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public OptionAttribute required() {
        this.isRequired = true;
        return this;
    }

    public OptionAttribute noArgument() {
        this.argument = false;
        return this;
    }

    public OptionAttribute setValue(String value) {
        this.value = value;
        return this;
    }
}
