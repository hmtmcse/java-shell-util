package com.hmtmcse.shellutil.test;

import com.hmtmcse.shellutil.base.CommandRequest;
import com.hmtmcse.shellutil.base.CommandResponse;
import com.hmtmcse.shellutil.base.OSCommandExec;
import com.hmtmcse.shellutil.common.ShellUtilException;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Main {



    public static void main(String[] args) {

        execTest();
        System.exit(0);

        args = new String[]{"build", "-m" , "first", "second", "-c"};
//        args = new String[]{"build", "-m" , "first", "second"};
        final Option verboseOption = Option.builder("v")
                .required(false)
                .desc("Print status with verbosity.")
                .build();
        final Option fileOption = Option.builder("f")
                .required()
                .hasArg()
                .desc("File to be processed.")
                .build();
        final Options options = new Options();
        options.addOption(verboseOption);
        options.addOption(fileOption);
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "ant", options );

    }


    public static void execTest() {
        OSCommandExec osCommandExec = new OSCommandExec();
        CommandResponse commandResponse = osCommandExec.execute(
                CommandRequest.withCommand("ping", "google.com")
                        .setPrintInConsole(true)
                        .setWaitUntilFinish(true)
        );

        if (!commandResponse.isExecuted) {
            System.out.println(commandResponse.exceptionMessage);
        }

    }

}
