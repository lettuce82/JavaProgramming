package com.nhnacademy;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.CommandLine;

public class Main {
    public static void main(String[] args) {
        //option
        Options options = new Options();
        Option port = Option.builder("l")
            .longOpt("port")
            .desc("port")
            .hasArg()
            .argName("port")
            .build();
        options.addOption(port);

        Option help = Option.builder("h")
            .longOpt("help")
            .desc("도움말")
            .build();
        options.addOption(help);

        //command
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);
            if (commandLine.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("day8", options);
            }
            if (commandLine.hasOption("port")) {
                String portNum= commandLine.getOptionValue("port");
                if (portNum.equals("1234")) {
                    //Server.clientMode();
                }
            }

        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}