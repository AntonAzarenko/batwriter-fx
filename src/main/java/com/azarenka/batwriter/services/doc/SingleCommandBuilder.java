package com.azarenka.batwriter.services.doc;

import com.azarenka.batwriter.domain.CommandToAppend;
import com.azarenka.batwriter.domain.Document;
import com.azarenka.batwriter.domain.TypeCommand;
import com.azarenka.batwriter.domain.TypeDocument;

import java.util.ArrayList;
import java.util.List;

public class SingleCommandBuilder implements Builder {

    private CommandToAppend command;
    private String prefix;

    @Override
    public Document buildDocument(CommandToAppend command) {
        this.command = command;
        Document document = new Document(command);
        document.setLinesOfDoc(buildDoc());
        return document;
    }

    private List<String> buildDoc() {
        List<String> strings = new ArrayList<>();
        setUpPrefix(command.getType());
        strings.add("@" + getDiskLater() + ":");
        strings.add(getCommand());
        strings.add(command.getTextCommand());
        strings.add(getFinal());
        return strings;
    }


    private String getFinal() {
        return command.getTypeDocument() == TypeDocument.CLOSABLE
            ? "@EXIT /B 0"
            : "";
    }

    private void setUpPrefix(TypeCommand type) {
        if (TypeCommand.CHANGE_DIR == type) {
            prefix = "cd";
        } else if (TypeCommand.START_APPLICATION == type) {
            prefix = "start";
        }
    }

    private String getCommand() {
        return prefix.equals("start")
            ? "\t@" + prefix + " \"\" \"" + command.getPathToFileExecute() + "\""
            : "\t@" + prefix + " " + command.getPathToFileExecute();
    }

    private String getDiskLater() {
        return command.getPath().substring(0, 1);
    }
}
