package com.azarenka.batwriter.services;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Writer extends FileWriter{
    private String fileName;
    private File file;

    public Writer(File file, String name) throws IOException {
        super(file);
        this.file = file;
        this.fileName = name;
    }

    public void writeDocument(List<String> strings) throws IOException {
        File dir = new File(file.getAbsolutePath()  + File.separator);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        BufferedReader stream = new BufferedReader(new FileReader(dir));
        strings.forEach(string -> {
            try {
                write(string);
                flush();
                write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        stream.close();
    }
}
