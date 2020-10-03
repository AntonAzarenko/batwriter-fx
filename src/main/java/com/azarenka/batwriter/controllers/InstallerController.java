package com.azarenka.batwriter.controllers;

import com.azarenka.batwriter.util.CommandBuilder;
import com.azarenka.batwriter.util.FilesSearcher;
import com.azarenka.batwriter.util.PropertiesLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * Description
 * <p>
 * (c) ant-azarenko@mail.ru 2020
 * </p>
 *
 * @author Anton Azarenka
 * Date 29.09.2020
 */
@Component
public class InstallerController {

    @Autowired
    private CommandBuilder commandBuilder;
    @Autowired
    private PropertiesLoader loader;
    @FXML
    private Button next, close;
    @FXML
    private CheckBox mavenCheckbox, gradlewCheckbox;

    public void initialize() {
    }

    public void next() throws IOException {

    }

    public void close() {
        System.exit(0);
    }
}
