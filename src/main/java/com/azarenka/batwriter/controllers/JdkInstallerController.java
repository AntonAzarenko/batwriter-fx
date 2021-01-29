package com.azarenka.batwriter.controllers;

import com.azarenka.batwriter.util.FilesSearcher;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Description
 * <p>
 * (c) ant-azarenko@mail.ru 2021
 * </p>
 *
 * Date 29.01.2021
 *
 * @author Anton Azarenka
 */
@Component
public class JdkInstallerController {

    @FXML
    private Label pathJdk;

    public void initialize() {
        FilesSearcher filesSearcher = new FilesSearcher();
        pathJdk.setText("Найдена установленная JDK " + filesSearcher.findJava().getPath());
    }

    public void next() {

    }

    public void close() {

    }
}
