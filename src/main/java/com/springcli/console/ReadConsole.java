package com.springcli.console;

import com.springcli.model.Project;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class ReadConsole {
    static private ReadConsole instance;
    Scanner scanner = new Scanner(System.in);

    public static ReadConsole getInstance() {
        if(Objects.isNull(instance)) {
            instance = new ReadConsole();
        }
        return instance;
    }

    private ReadConsole() {
    }

    public void explain() {
        System.out.println(red(bold("Welcome on SpringCLI for CRUD")));
        System.out.println("At any moment you could hint \"help\".");
        System.out.println("FirstStep : choose your architecture :");
    }

    public boolean askArchitecture()
    {
        String entityArch = "You could choose entity architecture, base on entities." +
                "A domain package will be created. For each of your entities, an associated package will be created.\n" +
                "In this package the entity class, a controller and a repository will then be created.\n";
        String controllerArch = "In the controller architecture, all entities will in a model package, " +
                "all controllers in a controller package and all repository in a repository package.";
        String schemaArch = getSchemaArch();
        String question = "So, do you prefer entity architecture ? (Y/n)";
        System.out.println(entityArch);
        System.out.println(controllerArch);
        System.out.println(schemaArch);
        System.out.println(question);
        String response = scanner.nextLine().toLowerCase(Locale.ROOT).trim();
        while (!response.isEmpty() && !response.equals("n") && !response.equals("y")) {
            System.out.println("I don't understand. Please hint Y or N");
            response = scanner.nextLine().toLowerCase(Locale.ROOT);
        }
        return !response.equals("n");
    }

    private String getSchemaArch() {
        String schemaArchStart =
                "                 " + bold("Entity Architecture") +
                "            #          " + bold("Controller Architecture") + "\n";
        String schemaArchMiddle = getSchemaArchMiddle();
        String schemaArchEnd =
                """
                         |                                              #    |
                         |---domain                                     #    |---model
                         |      |---entity1                             #    |    |---Entity1.java
                         |      |      |---Entity1.java                 #    |---controller
                         |      |      |---Entity1Controller.java       #    |    |---Entity1Controller.java
                         |      |      |---Entity1Repository.java       #    |---repository
                         |      |      |---Entity1DTO.java              #    |    |---Entity1Repository.java
                         |      |---entity2                             #    |---dto
                         |      |      |---Entity2.java                 #    |    |---Entity1DTO.java
                        """;
        return schemaArchStart + schemaArchMiddle + schemaArchEnd;
    }

    private String bold(String term) {
        return reset(ConstantsConsoleColor.ANSI_BOLD + term);
    }

    private String red(String term) {
        return reset(ConstantsConsoleColor.ANSI_RED + term);
    }

    private String reset(String term) {
        return term + ConstantsConsoleColor.ANSI_RESET;
    }

    private String getSchemaArchMiddle() {
        String base = "main                                            #   main\n";
        String projectName = Project.getInstance().getPackageName();
        int differenceBetweenMainAndNAme = projectName.length() - "main".length();
        String main = "main";
        for(int i = 0; i < differenceBetweenMainAndNAme; i += 1) {
            main = main + " ";
        }
        base = base.replace(main, projectName);
        base = base.replace("main", projectName);
        return base;
    }

}
