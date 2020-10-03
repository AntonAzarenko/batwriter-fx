package com.azarenka.batwriter.services;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
            System.out.println("Файл "+ fileName +" не найден");
            System.out.println("Попытка создать новый");
            try {
                Writer writer = new Writer(file, fileName);
                writer.writeDocument(Collections.EMPTY_LIST);
            } catch (IOException ioException) {
                System.out.println("Файл не создан");
            }
        }
        return stringList;
    }
}
