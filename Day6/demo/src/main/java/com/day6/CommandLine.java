package com.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CommandLine {
    static List<String> classPathList = new ArrayList<>();
    static List<String> moduleList = new ArrayList<>();
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ((args[i].trim().equals("--class-path")) || (args[i].trim().equals("-classpath"))) {
                if ((i + 1 == args.length)) {
                    throw new IllegalArgumentException();
                }
                String[] classPaths = args[i + 1].split(";");
                classPathList = Arrays.asList(classPaths);
                i++;
            }

            if ((args[i].trim().equals("--module"))|| (args[i].trim().equals("-m"))) {
                if (i + 1 == args.length) {
                    throw new IllegalArgumentException();
                }

                String[] modules = args[i + 1].split(",");
                moduleList = Arrays.asList(modules);
                i++;
            }
        }

        if (classPathList != null) {
            System.out.println("--class-path");
            for (String classPath : classPathList) {
                System.out.println(classPath);
            }
        }
        if (moduleList != null) {
            System.out.println("--module");
            for (String module : moduleList) {
                System.out.println(module);
            }
        }

        //내 풀이
        // String[] command;
        // for (String string : args) {
        //     command = string.split("\\s+");
        //     for (String word : command) {
        //         System.out.println(word);
        //     }
        // }
    }
}
