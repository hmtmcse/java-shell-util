package com.hmtmcse.shellutil.console.menu;

import com.hmtmcse.shellutil.common.ShellUtilException;

import java.util.LinkedHashMap;

public interface CLIMenuItemProcessor {
    public void process(LinkedHashMap<String, Object> params) throws ShellUtilException;
}
