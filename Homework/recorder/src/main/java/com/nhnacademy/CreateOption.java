package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CreateOption {
    Db db = new Db();
    Options options = new Options();
    

    public CreateOption() {
        Option add = Option.builder("a")
            .longOpt("add")
            .desc("테스트")
            .build();
        options.addOption(add);

        Option type = Option.builder("t")
            .longOpt("type")
            .hasArg()
            .argName("type")
            .desc("데이터의 종류를 지정합니다.")
            .build();
        options.addOption(type);

        Option id = Option.builder("i")
            .longOpt("id")
            .hasArg()
            .argName("id")
            .desc("아이디")
            .build();
        options.addOption(id);

        Option name = Option.builder("n")
            .longOpt("name")
            .hasArg()
            .argName("name")
            .desc("이름")
            .build();
        options.addOption(name);

        Option list = Option.builder("l")
            .longOpt("list")
            .desc("목록을 보여줍니다.")
            .build();
        options.addOption(list);

        Option count = Option.builder("c")
            .longOpt("count")
            .hasArg()
            .argName("count")
            .desc("대전 횟수")
            .build();
        options.addOption(count);

        Option win = Option.builder("W")
            .longOpt("Win")
            .hasArg()
            .argName("win")
            .desc("승리 횟수")
            .build();
        options.addOption(win);

        Option help = Option.builder("h")
            .longOpt("help")
            .desc("도움말")
            .build();
        options.addOption(help);

        Option model = Option.builder("M")
            .longOpt("model")
            .hasArg()
            .argName("model")
            .desc("모델명")
            .build();
        options.addOption(model);

        Option stamina = Option.builder("s")
            .longOpt("stamina")
            .hasArg()
            .argName("stamina")
            .desc("체력")
            .build();
        options.addOption(stamina);

        Option power = Option.builder("p")
            .longOpt("power")
            .hasArg()
            .argName("power")
            .desc("공격력")
            .build();
        options.addOption(power);

        Option defence = Option.builder("d")
            .longOpt("defence")
            .hasArg()
            .argName("defence")
            .desc("방어력")
            .build();
        options.addOption(defence);

        Option moveSpeed = Option.builder("m")
            .longOpt("moveSpeed")
            .hasArg()
            .argName("moveSpeed")
            .desc("이동속도")
            .build();
        options.addOption(moveSpeed);

        Option attackSpeed = Option.builder("A")
            .longOpt("attackSpeed")
            .hasArg()
            .argName("attackSpeed")
            .desc("공격속도")
            .build();
        options.addOption(attackSpeed);

        Option history = Option.builder("L")
            .longOpt("history")
            .hasArg()
            .argName("saved-file")
            .desc("변경이력")
            .build();
        options.addOption(history);

        Option dbFile = Option.builder("f")
            .longOpt("dbFile")
            .hasArg()
            .argName("saved-file")
            .desc("데이터 저장 파일")
            .build();
        options.addOption(dbFile);
        
    }
    
    public Options getOptions() {
        return options;
    }

    public void hasOption(CommandLine commandLine, String[] args, Db db) {
        if (commandLine.hasOption(options.getOption("help"))) {
            printHelp();
        }
    
        if (commandLine.hasOption(options.getOption("add"))) {
            handleAddOption(commandLine, db);
        }
        
        if (commandLine.hasOption(options.getOption("list"))) {
            handleListOption(commandLine, db);
        }
    
        System.out.println("인자 뭐가 있지? : " + commandLine.getArgList());
    }
    
    private void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("li", this.options);
    }
    
    private void handleAddOption(CommandLine commandLine, Db db) {
        if (commandLine.hasOption(options.getOption("type"))) {
            String typeValues = commandLine.getOptionValue(options.getOption("type"));
            if (commandLine.hasOption(options.getOption("id"))) {
                String idValues = commandLine.getOptionValue(options.getOption("id"));
                if (typeValues.equals("user")) {
                    String nameValues = commandLine.getOptionValue(options.getOption("name"));
                    String dbFileValues = commandLine.getOptionValue(options.getOption("dbFile"));
                    User user = new User(idValues, nameValues);
                    db.add(user, dbFileValues, typeValues);
                } else if (typeValues.equals("item")) {
                    String modelValues = commandLine.getOptionValue(options.getOption("model"));
                    String staminaValues = commandLine.getOptionValue(options.getOption("stamina"));
                    String powerValues = commandLine.getOptionValue(options.getOption("power"));
                    String defenceValues = commandLine.getOptionValue(options.getOption("defence"));
                    String moveSpeedValues = commandLine.getOptionValue(options.getOption("moveSpeed"));
                    String attackSpeedValues = commandLine.getOptionValue(options.getOption("attackSpeed"));
                    String dbFileValues = commandLine.getOptionValue(options.getOption("dbFile"));
                    Item item = new Item(idValues, modelValues, Integer.parseInt(staminaValues), Integer.parseInt(powerValues),
                        Integer.parseInt(defenceValues), Integer.parseInt(moveSpeedValues), Integer.parseInt(attackSpeedValues));
                    db.add(item, dbFileValues, typeValues);
                }
            }
        }
    }
    
    private void handleListOption(CommandLine commandLine, Db db) {
        if (commandLine.hasOption(options.getOption("type"))) {
            String typeValues = commandLine.getOptionValue(options.getOption("type"));
            if (commandLine.hasOption(options.getOption("dbFile"))) {
                String dbFileValues = commandLine.getOptionValue(options.getOption("dbFile"));
                db.list(typeValues, dbFileValues);
            }
        }
    }
}
