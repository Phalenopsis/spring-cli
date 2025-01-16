package com.springcli;

import com.springcli.console.ReadConsole;
import com.springcli.explorer.JavaProjectValidator;
import com.springcli.model.Project;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            JavaProjectValidator.getInstance().verifyProject();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }

        ReadConsole readConsole = ReadConsole.getInstance();

        readConsole.explain();
        boolean entityArchitecture = readConsole.askArchitecture();
        Project.getInstance().setEntityArchitecture(entityArchitecture);

    }
}