package com.azarenka.batwriter.services;

import com.azarenka.batwriter.domain.CommandToAppend;
import com.azarenka.batwriter.domain.TypeCommand;
import com.azarenka.batwriter.domain.TypeDocument;

import java.util.ArrayList;
import java.util.List;

public class BuilderStrings {

    private CommandToAppend command;
    private String prefix;
    private TypeDocument typeDocument;

    public BuilderStrings(CommandToAppend command) {
        this.command = command;
        this.typeDocument = command.getTypeDocument();
    }

    public List<String> buildDocument(List<String> oldDocument) {
        List<String> document;
        if (oldDocument.isEmpty()) {
            document = createNewDocument();
        } else {
            document = combineDocument(oldDocument);
        }
        return document;
    }

    private List<String> combineDocument(List<String> document) {
        document.add("");
        addCommandToDocument(document);
        return document;
    }

    private void addCommandToDocument(List<String> document) {
        setUpPrefix(command.getType());
        document.add(getCondition());
        document.add(getSeparator());
        document.add(getDescription(TypeCommand.valueOf(command.getType().name()) + " to " + command.getDescription()));
        document.add(getCommand());
        document.add(getFinalString());
    }

    private List<String> createNewDocument() {
        List<String> document = new ArrayList<>();
        addCommandToDocument(document);
        return document;
    }

    private String getCommand() {
        return prefix.equals("start")
                ? "\t@" + prefix + " \"\" \"" + command.getPathToFileExecute()+ "\""
                : "\t@" + prefix + " " + command.getPath();
    }

    private String getFinalString() {
        return ")";
    }

    private String getDescription(String description) {
        return "\t@echo " + description;
    }

    private String getCondition() {
        return "@if \"%1\"==\"" + command.getTextCommand() + "\" (";
    }

    private String getSeparator() {
        return "\t@echo ..................................................................................................";
    }

    private void setUpPrefix(TypeCommand type) {
        if (TypeCommand.CHANGE_DIR == type) {
            prefix = "cd";
        } else if (TypeCommand.START_APPLICATION == type) {
            prefix = "start";
        }
    }
}
