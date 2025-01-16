package com.springcli.explorer;

import com.springcli.model.Project;
import java.io.File;
import java.util.Objects;

public class JavaProjectValidator {
    private static JavaProjectValidator instance;

    public static JavaProjectValidator getInstance() {
        if (Objects.isNull(instance)) {
            return new JavaProjectValidator();
        }
        return instance;
    }

    private JavaProjectValidator() {

    }

    public void verifyProject() throws Exception {
        PomXmlExplorer.getInstance().verifyPomXml();
        verifySrcDir();
        verifyPackageDir();
    }

    private void verifySrcDir() throws Exception {
        String srcDirPath = Project.getInstance().getSrcPath();
        File srcDirFile = new File(srcDirPath);
        if(!(srcDirFile.exists() && srcDirFile.isDirectory())) {
            throw new Exception("src directory does not exists.");
        }
    }

    private void verifyPackageDir() throws Exception {
        String PackageDirPath = Project.getInstance().getPackagePath();
        File srcDirFile = new File(PackageDirPath);
        if(!(srcDirFile.exists() && srcDirFile.isDirectory())) {
            throw new Exception("package directory does not exists.");
        }
    }
}
