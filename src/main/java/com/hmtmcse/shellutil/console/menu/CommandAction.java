package com.hmtmcse.shellutil.console.menu;

import com.hmtmcse.shellutil.common.ShellUtilException;

public interface CommandAction {
    public void process(OptionValues optionValues) throws ShellUtilException;
}
