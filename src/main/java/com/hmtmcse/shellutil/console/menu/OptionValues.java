package com.hmtmcse.shellutil.console.menu;

import java.util.HashMap;

public class OptionValues {

    private HashMap<String, OptionAttribute> options = new HashMap<>();

    public OptionValues(HashMap<String, OptionAttribute> options){
        this.options = options;
    }

    public void setValue(String optionName, String value){
        if (options.get(optionName) != null){
            options.get(optionName).value = value;
        }
    }


    public static OptionValues init(HashMap<String, OptionAttribute> options){
        return new OptionValues(options);
    }

}
