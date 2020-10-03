package com.azarenka.batwriter.util;

import java.io.File;
import java.util.Objects;

/**
 * Description
 * <p>
 * (c) ant-azarenko@mail.ru 2020
 * </p>
 *
 * @author Anton Azarenka
 * Date 29.09.2020
 */
public class FilesSearcher {

    private String regex = "^jdk[0-9].+[0-9].+[0-9]";
    private String path = "c:\\Program files";
    private File jdk;

    public File findJava() {
        File file = new File(path);
        findFile(file);
        return jdk;
    }

    private void findFile(File baseDirectory) {
        if (Objects.nonNull(baseDirectory.listFiles())) {
            if (baseDirectory.isDirectory()) {
                for (File file : Objects.requireNonNull(baseDirectory.listFiles())) {
                    if (file.isFile()) {
                    } else {
                        if (file.getName().matches(regex)) {
                            jdk = file;
                        }
                        findFile(file);
                    }
                }
            }
        }
    }
}
