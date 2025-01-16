package com.springcli;

import com.springcli.console.ReadConsole;
import com.springcli.explorer.JavaProjectValidator;

public class Main {
    public static void main(String[] args) {
        try {
            JavaProjectValidator.getInstance().verifyProject();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }

        ReadConsole readConsole = ReadConsole.getInstance();
        readConsole.explain();
        boolean entityArchitecture = readConsole.askArchitecture();
        System.out.println(entityArchitecture);

        System.out.println();
    }


}