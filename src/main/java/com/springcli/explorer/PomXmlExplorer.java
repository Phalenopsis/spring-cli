package com.springcli.explorer;

import java.io.*;
import java.util.Objects;

public class PomXmlExplorer {
    private static PomXmlExplorer instance;
    private final String pomXmlPath;

    public static PomXmlExplorer getInstance() {
        if(Objects.isNull(instance)) {
            return new PomXmlExplorer();
        }
        return instance;
    }

    private PomXmlExplorer() {
        pomXmlPath = getPomXmlPath();
    }

    private String getPomXmlPath() {
        return  System.getProperty("user.dir") + File.separator  + "pom.xml";
    }

    public void verifyPomXml() throws Exception {
        File pomXmlFile = new File(pomXmlPath);
        if(!(pomXmlFile.exists() && !pomXmlFile.isDirectory())) {
            throw new Exception("pom.xml file does not exists.");
        }
    }

    public String getProjectPackage() {
        String packageName = "";
        try (
                BufferedReader reader = new BufferedReader(new FileReader(pomXmlPath))
        ) {
            String line;
            while(Objects.nonNull(line = reader.readLine())) {
                String OPENING_TAG = "<groupId>";
                String CLOSING_TAG = "</groupId>";
                if(line.contains(OPENING_TAG) && line.contains(CLOSING_TAG) ) {
                    String lineWithoutOpening = line.replace(OPENING_TAG, "");
                    packageName = lineWithoutOpening.replace(CLOSING_TAG, "").trim();
                }
            }
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
        return packageName;
    }
}
