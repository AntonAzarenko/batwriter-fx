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
 * (c) ant-azarenko@mail.ru 2021
 * </p>
 *
 * Date 29.01.2021
 *
 * @author Anton Azarenka
 */
@Component
public class JdkInstallerWindow extends CommonWindowsWidget {

    @Value(value = "classpath:fxml/.batwriter-jdk-installer.fxml")
    private Resource resource;
    private Scene jdkInstallerScene;
    private WindowLoader windowLoader;

    public JdkInstallerWindow(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public Scene getScene() {
        return jdkInstallerScene;
    }

    @PostConstruct
    public void init() {
        this.windowLoader = new WindowLoader(resource);
    }

    @Override
    public void loadBean() {
        FXMLLoader mainWidgetWindow = windowLoader.loadFxmlFile();
        mainWidgetWindow.setControllerFactory(applicationContext::getBean);
        Parent mainWidgetParent = getParent(mainWidgetWindow);
        jdkInstallerScene = new Scene(mainWidgetParent, super.getWidth(), super.getHeight());
    }
}
