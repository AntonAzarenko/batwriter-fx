package com.azarenka.batwriter.domain;

import com.azarenka.batwriter.services.Helper;

import java.util.Objects;

public class CommandToAppend {

    private TypeCommand typeCommand;
    private TypeDocument typeDocument;
    private TypeFileCommand typeFileCommand;
    private String pathToFileExecute;
    private String pathToDir;
    private String fileName;
    private String description;
    /**
     * @path Path to main file for multiple commands
     */
    private String path;
    private String application;
    private String textCommand;
    private Helper helper;
    private boolean sysEnv;
    private boolean newFile;

    public CommandToAppend(TypeCommand typeCommand, String fileName, String description, String path,
                           String application, String textCommand, Helper helper, TypeDocument typeDocument,
                           boolean sysEnv, String pathToFileExecute, boolean newFile, TypeFileCommand typeFileCommand) {
        this.typeCommand = typeCommand;
        this.fileName = fileName;
        this.description = description;
        this.path = path;
        this.application = application;
        this.textCommand = textCommand;
        this.helper = helper;
        this.typeDocument = typeDocument;
        this.sysEnv = sysEnv;
        this.pathToFileExecute = pathToFileExecute;
        this.newFile = newFile;
        this.typeFileCommand = typeFileCommand;
    }

    public TypeFileCommand getTypeFileCommand() {
        return typeFileCommand;
    }

    public void setTypeFileCommand(TypeFileCommand typeFileCommand) {
        this.typeFileCommand = typeFileCommand;
    }

    public boolean isNewFile() {
        return newFile;
    }

    public void setNewFile(boolean newFile) {
        this.newFile = newFile;
    }

    public String getPathToFileExecute() {
        return pathToFileExecute;
    }

    public void setPathToFileExecute(String pathToFileExecute) {
        this.pathToFileExecute = pathToFileExecute;
    }

    public boolean isSysEnv() {
        return sysEnv;
    }

    public void setSysEnv(boolean sysEnv) {
        this.sysEnv = sysEnv;
    }

    public TypeCommand getTypeCommand() {
        return typeCommand;
    }

    public void setTypeCommand(TypeCommand typeCommand) {
        this.typeCommand = typeCommand;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }

    public TypeCommand getType() {
        return typeCommand;
    }

    public void setType(TypeCommand type) {
        this.typeCommand = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getTextCommand() {
        return textCommand;
    }

    public void setTextCommand(String textCommand) {
        this.textCommand = textCommand;
    }

    public Helper getHelper() {
        return helper;
    }

    public void setHelper(Helper helper) {
        this.helper = helper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandToAppend that = (CommandToAppend) o;
        return typeCommand == that.typeCommand &&
                Objects.equals(description, that.description) &&
                Objects.equals(path, that.path) &&
                Objects.equals(application, that.application) &&
                Objects.equals(textCommand, that.textCommand) &&
                Objects.equals(helper, that.helper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeCommand, description, path, application, textCommand, helper);
    }

    @Override
    public String toString() {
        return "CommandToAppend{" +
                "type=" + typeCommand +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", application='" + application + '\'' +
                ", textCommand='" + textCommand + '\'' +
                ", helper=" + helper +
                '}';
    }
}
