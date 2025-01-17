package com.springcli.model;

import com.springcli.explorer.PomXmlExplorer;
import java.io.File;
import java.util.Objects;

public class Project {
    private final PackageName packageName;
    private final String userPath;
    private final String packagePath;

    private boolean entityArchitecture = true;

    private static Project instance;

    public static Project getInstance() {
        if(Objects.isNull(instance)) {
            String packageNameString = PomXmlExplorer.getInstance().getProjectPackage();
            return new Project(packageNameString);
        }
        return instance;
    }

    private Project(String pPackageName) {
        packageName = new PackageName(pPackageName);
        userPath = System.getProperty("user.dir") + File.separator;
        packagePath = getSrcPath() + File.separator + "main" +
                File.separator + "java" + File.separator + packageName.getPath();
    }

    public String getPackageName() {
        return packageName.getCompleteName();
    }

    public String getSrcPath() {
        return userPath + File.separator + "src";
    }

    public String getPackagePath() {
        return packagePath;
    }

    public String getUserPath() {
        return userPath;
    }

    public boolean isEntityArchitecture() {
        return entityArchitecture;
    }

    public void setEntityArchitecture(boolean entityArchitecture) {
        this.entityArchitecture = entityArchitecture;
    }

    public String getEntityPath(String entity) {
        return entityArchitecture ? getEntityPathInEntityArch(entity) :
                packagePath + File.separator + "model";
    }

    private String getEntityPathInEntityArch(String entity) {
        return packagePath + File.separator + "domain" + File.separator + entity;
    }
}
