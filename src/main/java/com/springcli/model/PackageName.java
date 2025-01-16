package com.springcli.model;

import java.io.File;

public class PackageName {
    private final String extension;
    private final String name;

    public PackageName(String packageName) {
        String[] arr = packageName.split("\\.");
        extension = arr[0];
        name = arr[1];
    }

    public String getExtension() {
        return extension;
    }

    public String getName() {
        return name;
    }

    public String getCompleteName() {
        return extension + "." + name;
    }

    public String getPath() {
        return extension + File.separator + name;
    }
}
