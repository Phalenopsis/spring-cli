package com.springcli;

import com.springcli.console.ReadConsole;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            verifyProject();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }

        ReadConsole readConsole = ReadConsole.getInstance();
        readConsole.explain();
        boolean entityArchitecture = readConsole.askArchitecture();
        System.out.println(entityArchitecture);

        System.out.println();
    }

    public static void verifyProject() throws Exception {
        String path = System.getProperty("user.dir");
        System.out.println(path);
        String pomXml = path + File.separator  + "pom.xml";
        File pomXmlFile = new File(pomXml);
        if(!(pomXmlFile.exists() && !pomXmlFile.isDirectory())) {
            throw new Exception("pom.xml file does not exists.");
        }
        String srcDirPath = path + File.separator + "src";
        File srcDirFile = new File(srcDirPath);
        if(!(srcDirFile.exists() && srcDirFile.isDirectory())) {
            throw new Exception("src directory does not exists.");
        }

    }
}