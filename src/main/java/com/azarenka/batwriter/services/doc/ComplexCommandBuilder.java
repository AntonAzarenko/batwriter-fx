package com.azarenka.batwriter.services.doc;

import com.azarenka.batwriter.api.Builder;
import com.azarenka.batwriter.domain.CommandToAppend;
import com.azarenka.batwriter.domain.Document;
import com.azarenka.batwriter.domain.TypeCommand;
import com.azarenka.batwriter.services.HelpBuilder;
import com.azarenka.batwriter.services.Reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComplexCommandBuilder implements Builder {

    private CommandToAppend command;
    private Reader reader;
    private String prefix;

    public ComplexCommandBuilder() {
        reader = new Reader();
    }

    @Override
    public Document buildDocument(CommandToAppend command) throws IOException {
        this.command = command;
        Document document = new Document(command);
        document.setLinesOfDoc(buildDoc());
        document.setCommandToAppend(command);
        return document;
    }

    private List<String> buildDoc() throws IOException {
        List<String> doc = buildDocument(reader.getDocument(
                command.getPath() + File.separator + command.getFileName()));
        HelpBuilder builder = new HelpBuilder(command);
        return builder.buildDoc(doc);
    }

    private List<String> buildDocument(List<String> oldDocument) {
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
        document.add("@" + getDiskLetter()+ ":");
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
                : "\t@" + prefix + " " + command.getPathToFileExecute();
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

    private String getDiskLetter() {
        return command.getPathToFileExecute().substring(0,1);
    }
}
