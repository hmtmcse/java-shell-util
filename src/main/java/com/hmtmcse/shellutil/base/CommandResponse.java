package com.hmtmcse.shellutil.base;

import java.util.ArrayList;
import java.util.List;

public class CommandResponse {

    public Boolean isExecuted = true;
    public Integer exitCode;
    public String commandOutput;
    public List<String> commandOutputLine = new ArrayList<>();
    public String errorOutput;
    public List<String> errorOutputLine = new ArrayList<>();
    public String exceptionMessage = null;

}
