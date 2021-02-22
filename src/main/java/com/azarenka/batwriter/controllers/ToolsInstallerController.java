package com.azarenka.batwriter.controllers;

import com.azarenka.batwriter.SceneChanger;
import com.azarenka.batwriter.services.command.infrastructure.ICommandCreator;
import com.azarenka.batwriter.api.ICommand;
import com.azarenka.batwriter.services.command.infrastructure.SystemVariableInitializer;
import com.azarenka.batwriter.util.PropertiesLoader;
import com.azarenka.batwriter.windows.MainWindow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
public class ToolsInstallerController {

    @Autowired
    private PropertiesLoader loader;
    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private MainWindow mainWindow;
    @FXML
    private CheckBox mavenInstallCheckbox, gradlewInstallCheckbox;
    @FXML
    private Label mavenInstallOk, gradlewInstallOk;
    @FXML
    private Button next, close;
    private String mavenPath, gradlewPath;

    private static ICommandCreator ICommandCreator;

    public void initialize() {
        initPaths();
    }

    public void next() {
        if (mavenInstallCheckbox.isSelected()) {
            boolean isOk = installSystemVariable(mavenPath, "mvn", "path");
            if (isOk) {
                mavenInstallOk.setText("Ok");
            } else {
                mavenInstallOk.setText("Negative");
            }
        }
        if (gradlewInstallCheckbox.isSelected()) {
            boolean isOk = installSystemVariable(gradlewPath, "gradle", "path");
            if (isOk) {
                gradlewInstallOk.setText("Ok");
            } else {
                gradlewInstallOk.setText("Negative");
            }
        }
        //mainWindow.loadBean();
        sceneChanger.setNewScene(mainWindow.getScene());
    }

    private void initPaths() {
        this.mavenPath = loader.getProperties("application.file.maven_path");
        this.gradlewPath = loader.getProperties("application.file.gradle_path");
    }

    private boolean installSystemVariable(String path, String... var) {
        ICommand commandCreator = initCommandCreator();
        String command = commandCreator.getCommand(path, var);
        System.out.println(command);
        try {
            executeCommand(command);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private ICommand initCommandCreator() {
        ICommandCreator = new SystemVariableInitializer(loader);
        return ICommandCreator.initCreator();
    }

    public void executeCommand(String command) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec(command);
    }

    public void close() {
        System.exit(0);
    }
}
