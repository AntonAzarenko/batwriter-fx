package com.azarenka.batwriter;

import com.azarenka.batwriter.windows.JdkInstallerWindow;
import com.azarenka.batwriter.windows.MainWindow;
import com.azarenka.batwriter.windows.ToolsInstallerWindow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import javafx.scene.Scene;

@Component
public class SceneChanger extends StageInitializer {

    @Value("${application.window.resizable}")
    private boolean resizable;

    @Autowired
    private ToolsInstallerWindow toolsInstallerWindow;
    @Autowired
    private JdkInstallerWindow jdkInstallerWindow;
    @Autowired
    private MainWindow mainWindow;

    public SceneChanger(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public void setNewScene(Scene scene) {
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(resizable);
        stage.show();
    }

    public void setGreetingWindow() {
        setNewScene(greetingWindow.getScene());
    }

    public void setMainWindow() {
        setNewScene(mainWindow.getScene());
    }

    public void setJdkInstallerWindow() {
        setNewScene(jdkInstallerWindow.getScene());
    }

    public void setToolsInstallerWindow() {
        setNewScene(toolsInstallerWindow.getScene());
    }

    @PostConstruct
    private void init() {
        jdkInstallerWindow.loadBean();
        toolsInstallerWindow.loadBean();
        mainWindow.loadBean();
    }
}
