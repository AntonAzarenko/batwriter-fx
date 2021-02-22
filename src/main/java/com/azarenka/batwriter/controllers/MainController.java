package com.azarenka.batwriter.controllers;

import com.azarenka.batwriter.SceneChanger;
import com.azarenka.batwriter.api.ICommand;
import com.azarenka.batwriter.api.IEnvironmentSetter;
import com.azarenka.batwriter.domain.PropertiesApp;
import com.azarenka.batwriter.domain.TypeCommand;
import com.azarenka.batwriter.domain.TypeFileCommand;
import com.azarenka.batwriter.services.Executor;
import com.azarenka.batwriter.services.command.infrastructure.ChangeDirInitializer;
import com.azarenka.batwriter.services.command.infrastructure.ICommandCreator;
import com.azarenka.batwriter.services.command.infrastructure.StarterInitializer;
import com.azarenka.batwriter.services.command.infrastructure.SystemVariableInitializer;
import com.azarenka.batwriter.services.util.DialogsUtilService;
import com.azarenka.batwriter.util.CommandBuilder;
import com.azarenka.batwriter.util.PropertiesLoader;
import com.azarenka.batwriter.windows.SettingsWindow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

@Component
public class MainController {
    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private DialogsUtilService dialogsUtilService;
    @Autowired
    private PropertiesLoader loader;
    @Autowired
    private CommandBuilder commandBuilder;
    @Autowired
    private SettingsWindow settingsWindow;
    @Autowired
    private IEnvironmentSetter environmentSetter;
    @FXML
    public ChoiceBox<TypeCommand> commandChoiceBox;
    @FXML
    public CheckBox commonFile;
    @FXML
    TextField command;
    @FXML
    TextField description;
    @FXML
    private TextField pathLabel;
    private Map<TypeCommand, ICommandCreator> commandMap;

    private File path;

    public void setSettingScene() {
        settingsWindow.loadBean();
        sceneChanger.setNewScene(settingsWindow.getScene());
    }

    public void addCommand() throws IOException {
        ICommand commandCreator = commandMap.get(commandChoiceBox.getValue()).initCreator();
        String command = commandCreator.getCommand(pathLabel.getText());
        System.out.println(command);
        PropertiesApp propertiesApp = loader.loadProperties();
        Executor runner = new Executor(commandBuilder
            .setFileName(getFileName())
            .setPath(propertiesApp.getPath())
            .setDescription(description.getText())
            .setTypeFileCommand(TypeFileCommand.SINGLE)
            .setTypeCommand(commandChoiceBox.getValue())
            .setTypeDocument(commandChoiceBox.getValue())
            .setPathToFileExecute(pathLabel.getText())
            .setTextCommand(command)
            .setNewFile(commonFile.isSelected())
            .build());
        runner.execute();
    }

    public void initialize() {
        initCommandChoiceBox();
        initCommandMap();
    }

    public void choiceFile() {
        pathLabel.setText(
            dialogsUtilService.showDialog(loader.loadProperties().getPath(), isFolder()).getAbsolutePath());
    }

    /**
     * Returns command as file name if user choose {@link TypeCommand#START_APPLICATION} and checkbox newFile is selected.
     * otherwise returns file name from properties
     *
     * @return name of file
     */
    private String getFileName() {
        return commandChoiceBox.getValue().equals(TypeCommand.START_APPLICATION) && !commonFile.isSelected()
            ? command.getText() + CommandBuilder.postfix
            : loader.loadProperties().getFileName() + CommandBuilder.postfix;
    }

    private boolean isFolder() {
        return commandChoiceBox.getValue().equals(TypeCommand.CHANGE_DIR);
    }

    private void initCommandMap() {
        commandMap = new HashMap<>();
        commandMap.put(TypeCommand.START_APPLICATION, new StarterInitializer());
        commandMap.put(TypeCommand.CHANGE_DIR, new ChangeDirInitializer());
        commandMap.put(TypeCommand.SYSTEM_VARIABLE, new SystemVariableInitializer(loader));
    }

    private void initCommandChoiceBox() {
        commandChoiceBox.setItems(FXCollections.observableArrayList(TypeCommand.values()));
        commandChoiceBox.setValue(TypeCommand.START_APPLICATION);
    }
}
