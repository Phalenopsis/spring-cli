package com.springcli.explorer;

import java.io.File;
import java.util.Objects;

public class JavaProjectValidator {
    private static JavaProjectValidator instance;
    private final String path;

    public static JavaProjectValidator getInstance() {
        if (Objects.isNull(instance)) {
            return new JavaProjectValidator();
        }
        return instance;
    }

    private JavaProjectValidator() {
        path = System.getProperty("user.dir");
    }


    public void verifyProject() throws Exception {
        verifyPomXml();
        verifySrcDir();
    }

    public void verifyPomXml() throws Exception {
        String pomXml = path + File.separator  + "pom.xml";
        File pomXmlFile = new File(pomXml);
        if(!(pomXmlFile.exists() && !pomXmlFile.isDirectory())) {
            throw new Exception("pom.xml file does not exists.");
        }
    }

    private void verifySrcDir() throws Exception {
        String srcDirPath = path + File.separator + "src";
        File srcDirFile = new File(srcDirPath);
        if(!(srcDirFile.exists() && srcDirFile.isDirectory())) {
            throw new Exception("src directory does not exists.");
        }
    }
}
