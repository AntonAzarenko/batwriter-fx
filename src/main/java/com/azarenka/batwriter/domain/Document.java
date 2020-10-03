package com.azarenka.batwriter.domain;

import java.util.List;

public class Document {

    private CommandToAppend commandToAppend;
    private List<String> linesOfDoc;

    public Document(CommandToAppend commandToAppend) {
        this.commandToAppend = commandToAppend;
    }

    public List<String> getLinesOfDoc() {
        return linesOfDoc;
    }

    public void setLinesOfDoc(List<String> linesOfDoc) {
        this.linesOfDoc = linesOfDoc;
    }

    public CommandToAppend getCommandToAppend() {
        return commandToAppend;
    }

    public void setCommandToAppend(CommandToAppend commandToAppend) {
        this.commandToAppend = commandToAppend;
    }
}
