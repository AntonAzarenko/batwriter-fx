package com.azarenka.batwriter.controllers;

import com.azarenka.batwriter.SceneChanger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

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
public class GreetingController {

    @Autowired
    private SceneChanger sceneChanger;
    @FXML
    public TextArea textArea;

    public void initialize() {
        textArea.setText(
            "//TODO write a greeting");
    }

    public void next() {
        sceneChanger.setJdkInstallerWindow();
    }

    public void skip() {
        sceneChanger.setMainWindow();
    }

    public void close() {
        System.exit(0);
    }
}
