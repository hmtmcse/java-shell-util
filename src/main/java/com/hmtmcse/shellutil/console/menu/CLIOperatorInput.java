package com.hmtmcse.shellutil.console.menu;

import java.util.LinkedHashMap;

public class CLIOperatorInput {

    private LinkedHashMap<String, Object> params = new LinkedHashMap<>();

    public String getString(String key){
        Object value = params.get(key);
        if (value != null){
            return value.toString();
        }
        return null;
    }

    public Boolean getBoolean(String key){
        return Boolean.valueOf(getString(key));
    }
}
