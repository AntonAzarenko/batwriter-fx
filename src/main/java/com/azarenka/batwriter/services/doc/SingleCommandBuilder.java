package com.azarenka.batwriter.services.doc;

import com.azarenka.batwriter.api.Builder;
import com.azarenka.batwriter.domain.CommandToAppend;
import com.azarenka.batwriter.domain.Document;
import com.azarenka.batwriter.domain.TypeDocument;

import java.util.Arrays;

public class SingleCommandBuilder implements Builder {

    @Override
    public Document buildDocument(CommandToAppend command) {
        Document document = new Document(command);
        document.setLinesOfDoc(Arrays.asList(command.getTextCommand(), getFinal(command)));
        return document;
    }

    private String getFinal(CommandToAppend command) {
        return command.getTypeDocument() == TypeDocument.CLOSABLE
            ? "@EXIT /B 0"
            : "";
    }
}
