package com.springcli.model;

import com.springcli.explorer.PomXmlExplorer;
import java.io.File;
import java.util.Objects;

public class Project {
    private final PackageName packageName;
    private final String userPath;

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
    }

    public String getPackageName() {
        return packageName.getCompleteName();
    }

    public String getSrcPath() {
        return userPath + File.separator + "src";
    }

    public String getPackagePath() {
        return getSrcPath() + File.separator + "main" + File.separator + "java" + File.separator + packageName.getPath();
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
}
