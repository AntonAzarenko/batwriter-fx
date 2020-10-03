package com.azarenka.batwriter.domain;

import com.azarenka.batwriter.util.PropertiesLoader;

public class PropertiesApp {

    private String path;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
