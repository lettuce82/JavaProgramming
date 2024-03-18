package com.day6;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
//import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Option {
    public static void main(String[] args) throws ParseException {
        Options options = new Options();

        // Builder option = org.apache.commons.cli.Option.builder("class-path");
        // option.hasArg();
        // option.desc("Class Path");
        // Option classPath1 = Option.build();
        // 아래와 같음

        org.apache.commons.cli.Option classPath = org.apache.commons.cli.Option.builder("classpath")
            .longOpt("class-path")
            .hasArg()
            .argName("path")
            .desc("Class Path")
            .build();
        options.addOption(classPath);

        org.apache.commons.cli.Option module = org.apache.commons.cli.Option.builder("m")
            .longOpt("module")
            .hasArg()
            .argName("module")
            .desc("Modules")
            .build();
        options.addOption(module);

        org.apache.commons.cli.Option group = org.apache.commons.cli.Option.builder("g")
            .desc("Global")
            .build();
        options.addOption(group);

        org.apache.commons.cli.Option version = org.apache.commons.cli.Option.builder("v")
            .desc("Version")
            .build();
        options.addOption(version);

        org.apache.commons.cli.Option help = org.apache.commons.cli.Option.builder("h")
            .longOpt("help")
            .desc("Help")
            .build();
        options.addOption(help);


        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("li", options);
            System.exit(0);
        }

        if (cmd.hasOption(classPath.getOpt())) {
            String classPathValue = cmd.getOptionValue(classPath.getOpt());
            System.out.println("Class Path: " + classPathValue);  
        }

        if (cmd.hasOption(module.getOpt())) {
            System.out.println("Module: ");  
        }

        if (cmd.hasOption(group.getOpt())) {
            System.out.println("Group: ");  
        }

        if (cmd.hasOption(version.getOpt())) {
            System.out.println("Version: 1.0.0");  
            System.exit(0);
        }

        System.out.println(cmd.getArgList());
    }
}
