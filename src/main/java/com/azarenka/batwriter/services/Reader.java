package com.azarenka.batwriter.services;

import static java.util.Collections.EMPTY_LIST;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private BufferedReader reader;

    public List<String> getDocument(String fileName) throws IOException {
        List<String> stringList = new ArrayList<>();
        File file = new File(fileName);
        try {
            reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                stringList.add(reader.readLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + fileName + " не найден");
            System.out.println("Попытка создать новый");
            try {
                Writer writer = new Writer(file, fileName);
                writer.writeDocument(EMPTY_LIST);
            } catch (IOException ioException) {
                System.out.println("Файл не создан");
            }
        }
        return stringList;
    }
}
