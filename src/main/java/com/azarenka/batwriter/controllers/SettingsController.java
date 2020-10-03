package com.azarenka.batwriter.controllers;

import com.azarenka.batwriter.SceneChanger;
import com.azarenka.batwriter.util.PropertiesLoader;
import com.azarenka.batwriter.windows.MainWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;

@Component
public class SettingsController {
    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private MainWindow mainWindow;
    @Autowired
    PropertiesLoader loader;
    @FXML
    public Label pathLabel;
    private File path;

    public void setMainWindowScene() {
        mainWindow.loadBean();
        sceneChanger.setNewScene(mainWindow.getScene());
    }

    public void initialize() {
        pathLabel.setText(loader.loadProperties().getPath());
        path = new File(loader.loadProperties().getPath());
    }
}
