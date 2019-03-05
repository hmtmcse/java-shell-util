package com.hmtmcse.shellutil.console.menu;

import java.util.HashMap;

public class OptionValues {

    private HashMap<String, OptionAttribute> options = new HashMap<>();

    public void setValue(OptionAttribute optionAttribute, String value){
        if (value != null){
            optionAttribute.value = value;
        }else if (optionAttribute.defaultValue != null){
            optionAttribute.value = optionAttribute.defaultValue;
        }
        options.put(optionAttribute.name, optionAttribute);
    }

    public Boolean asBoolean(String name){
        if (options.get(name) != null && options.get(name).value != null){
            return Boolean.valueOf(options.get(name).value);
        }
        return null;
    }

    public String asString(String name){
        if (options.get(name) != null && options.get(name).value != null){
            return options.get(name).value;
        }
        return null;
    }

    public Integer asInteger(String name){
        if (options.get(name) != null && options.get(name).value != null){
            return Integer.parseInt(options.get(name).value);
        }
        return null;
    }

    public Double asDouble(String name){
        if (options.get(name) != null && options.get(name).value != null){
            return Double.parseDouble(options.get(name).value);
        }
        return null;
    }


    public static OptionValues init(){
        return new OptionValues();
    }

}
