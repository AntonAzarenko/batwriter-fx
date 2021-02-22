package com.azarenka.batwriter.controllers;

import com.azarenka.batwriter.SceneChanger;
import com.azarenka.batwriter.util.FilesSearcher;
import com.azarenka.batwriter.windows.ToolsInstallerWindow;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private ToolsInstallerWindow toolsInstallerWindow;
    @FXML
    private Label pathJdk;

    public void initialize() {
        FilesSearcher filesSearcher = new FilesSearcher();
        pathJdk.setText("Мы установили вам JDK8. Путь: " + filesSearcher.findJava().getPath());
    }

    public void next() {
        sceneChanger.setNewScene(toolsInstallerWindow.getScene());
    }

    public void close() {
        System.exit(0);
    }
}
