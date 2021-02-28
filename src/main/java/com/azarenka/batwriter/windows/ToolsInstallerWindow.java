package com.azarenka.batwriter.windows;

import com.azarenka.batwriter.CommonWindowsWidget;
import com.azarenka.batwriter.WindowLoader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

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
public class ToolsInstallerWindow extends CommonWindowsWidget {

    @Value(value = "classpath:fxml/.batwriter-installer.fxml")
    private Resource resource;
    private Scene installerScene;
    private WindowLoader windowLoader;

    public ToolsInstallerWindow(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public Scene getScene() {
        return installerScene;
    }

    public void setScene(Scene main) {
        this.installerScene = main;
    }

    @PostConstruct
    public void init() {
        this.windowLoader = new WindowLoader(resource);
    }

    @Override
    public void loadBean() {
        FXMLLoader mainWidgetWindow = windowLoader.loadFxmlFile();
        mainWidgetWindow.setControllerFactory(aClass -> applicationContext.getBean(aClass));
        Parent mainWidgetParent = getParent(mainWidgetWindow);
        installerScene = new Scene(mainWidgetParent, super.getWidth(), super.getHeight());
    }
}
