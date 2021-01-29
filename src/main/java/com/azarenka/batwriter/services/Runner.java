package com.azarenka.batwriter.services;

import com.azarenka.batwriter.domain.CommandToAppend;
import com.azarenka.batwriter.domain.Document;
import com.azarenka.batwriter.domain.TypeFileCommand;
import com.azarenka.batwriter.api.Builder;
import com.azarenka.batwriter.services.doc.ComplexCommandBuilder;
import com.azarenka.batwriter.services.doc.SingleCommandBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Runner {
    private Writer writer;
    private CommandToAppend commandToAppend;
    private Builder builder;

    public Runner(CommandToAppend command) {
        this.commandToAppend = command;
    }

    public void start() throws IOException {
        if(commandToAppend.getTypeFileCommand().equals(TypeFileCommand.SINGLE)) {
            builder = new SingleCommandBuilder();
            Document document = builder.buildDocument(commandToAppend);
            write(document.getLinesOfDoc());
        }else {
            builder = new ComplexCommandBuilder();
            Document document = builder.buildDocument(commandToAppend);
            write(document.getLinesOfDoc());
        }
    }

    public void execute(String command) {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(command);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void write(List<String> doc)  {
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
}
