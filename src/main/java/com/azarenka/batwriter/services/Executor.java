package com.azarenka.batwriter.services;

import com.azarenka.batwriter.api.Builder;
import com.azarenka.batwriter.domain.CommandToAppend;
import com.azarenka.batwriter.domain.Document;
import com.azarenka.batwriter.domain.TypeFileCommand;
import com.azarenka.batwriter.services.doc.ComplexCommandBuilder;
import com.azarenka.batwriter.services.doc.SingleCommandBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Executor {
    private Writer writer;
    private CommandToAppend commandToAppend;
    private Builder builder;

    private Map<TypeFileCommand, Builder> builderMap;

    public Executor(CommandToAppend command) {
        this.commandToAppend = command;
        initBuilderMap();
    }

    public void execute() throws IOException {
        Builder builder = builderMap.get(commandToAppend.getTypeFileCommand());
        Document document = builder.buildDocument(commandToAppend);
        write(document.getLinesOfDoc());
    }

    private void write(List<String> doc) {
        File file = new File(commandToAppend.getPath() + File.separator + commandToAppend.getFileName());
        try {
            File temp = new File(commandToAppend.getPath());
            temp.mkdir();
            boolean newFile = file.createNewFile();
            writer = new Writer(file, commandToAppend.getFileName());
            writer.writeDocument(doc);
            writer.close();
        } catch (FileNotFoundException fe) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initBuilderMap() {
        builderMap = new HashMap<>();
        builderMap.put(TypeFileCommand.SINGLE, new SingleCommandBuilder());
        builderMap.put(TypeFileCommand.MULTIPLE, new ComplexCommandBuilder());
    }
}
