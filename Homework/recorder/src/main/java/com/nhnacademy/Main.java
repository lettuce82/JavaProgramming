package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
    public static void main(String[] args) {
        CreateOption createOption = new CreateOption();
        Options options = createOption.getOptions();
        Db db = new Db();

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);
            createOption.hasOption(commandLine, args, db);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}