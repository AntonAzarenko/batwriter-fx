package com.azarenka.batwriter.util;

import com.azarenka.batwriter.domain.CommandToAppend;
import com.azarenka.batwriter.domain.TypeCommand;
import com.azarenka.batwriter.domain.TypeDocument;
import com.azarenka.batwriter.domain.TypeFileCommand;
import com.azarenka.batwriter.services.Helper;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class CommandBuilder {

    public final static String postfix = ".bat";

    private TypeCommand typeCommand;
    private String fileName;
    private String pathToFileExecute;
    private String description;
    private String path;
    private String application;
    private String textCommand;
    private Helper helper;
    private TypeDocument typeDocument;
    private boolean sysEnv;
    private boolean newFile;
    private TypeFileCommand typeFileCommand;

    public CommandBuilder setTypeFileCommand(TypeFileCommand typeFileCommand) {
        this.typeFileCommand = typeFileCommand;
        return this;
    }

    public CommandBuilder setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public CommandBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public CommandBuilder setPathToFileExecute(String pathToFileExecute){
        this.pathToFileExecute = pathToFileExecute;
        return this;
    }

    public CommandBuilder setTypeCommand(TypeCommand typeCommand) {
        this.typeCommand = typeCommand;
        return this;
    }

    public CommandBuilder setTypeDocument(TypeCommand typeCommand) {
        this.typeDocument = choice(typeCommand);
        return this;
    }

    public CommandBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandBuilder setsysEnv(boolean sysEnv) {
        this.sysEnv = sysEnv;
        return this;
    }

    public CommandBuilder setNewFile(boolean newFile) {
        this.newFile = newFile;
        return this;
    }

    public CommandBuilder setTextCommand(String textCommand) {
        this.textCommand = textCommand;
        return this;
    }

    public CommandToAppend build() {
        return new CommandToAppend(typeCommand, fileName, description, path, "", textCommand, helper, typeDocument,
                sysEnv, pathToFileExecute, newFile,typeFileCommand);
    }

    private TypeDocument choice(TypeCommand typeCommand) {
        return typeCommand.equals(TypeCommand.START_APPLICATION)
                ? TypeDocument.CLOSABLE
                : TypeDocument.NOT_CLOSABLE;
    }
}
