package com.azarenka.batwriter.controllers;

import com.azarenka.batwriter.SceneChanger;
import com.azarenka.batwriter.domain.PropertiesApp;
import com.azarenka.batwriter.domain.TypeCommand;
import com.azarenka.batwriter.services.Runner;
import com.azarenka.batwriter.api.IEnvironmentSetter;
import com.azarenka.batwriter.util.CommandBuilder;
import com.azarenka.batwriter.util.PropertiesLoader;
import com.azarenka.batwriter.windows.SettingsWindow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class MainController {
    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private DialogsController dialogsController;
    @Autowired
    private PropertiesLoader loader;
    @Autowired
    private CommandBuilder commandBuilder;
    @Autowired
    private SettingsWindow settingsWindow;
    @Autowired
    private IEnvironmentSetter environmentSetter;
    @FXML
    public Label pathLabel;
    @FXML
    public ChoiceBox<String> commandChoiceBox;
    @FXML
    public CheckBox var;
    @FXML
    public CheckBox newFile;
    @FXML
    TextField command;
    @FXML
    TextField description;

    private File path;

    public void setSettingScene() {
        settingsWindow.loadBean();
        sceneChanger.setNewScene(settingsWindow.getScene());
    }

    public void addCommand() throws IOException {
        PropertiesApp propertiesApp = loader.loadProperties();
        Runner runner = new Runner(commandBuilder
            .setFileName(getFileName())
            .setPath(propertiesApp.getPath())
            .setDescription(description.getText())
            .setsysEnv(var.isSelected())
            //.setTypeCommand(commandChoiceBox.getValue().)
            //.setTypeDocument(commandChoiceBox.getValue())
            .setPathToFileExecute(pathLabel.getText())
            .setTextCommand(command.getText())
            .setNewFile(newFile.isSelected())
            .build());
        runner.start();
    }

    public void initialize() {
        List<String> str = new ArrayList<>();
        TypeCommand[] values = TypeCommand.values();
        IntStream.range(0, values.length).forEach(i -> str.add(values[i].getType()));
        commandChoiceBox.setItems(FXCollections.observableArrayList(str));
        commandChoiceBox.setValue(TypeCommand.START_APPLICATION.getType());
    }

    public void choiceFile() {
        pathLabel.setText(
            dialogsController.showDialog(loader.loadProperties().getPath(), isFolder()).getAbsolutePath());
    }

    /**
     * Returns command as file name if user choose {@link TypeCommand#START_APPLICATION} and checkbox newFile is selected.
     * otherwise returns file name from properties
     *
     * @return name of file
     */
    private String getFileName() {
        return commandChoiceBox.getValue().equals(TypeCommand.START_APPLICATION) && newFile.isSelected()
            ? command.getText() + CommandBuilder.postfix
            : loader.loadProperties().getFileName() + CommandBuilder.postfix;
    }

    boolean isFolder() {
        return commandChoiceBox.getValue().equals(TypeCommand.CHANGE_DIR);
    }
}
