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
public class InstallerWindow extends CommonWindowsWidget {

    @Value(value = "classpath:fxml/.batwriter-installer.fxml")
    private Resource resource;
    private Scene main;
    private FXMLLoader mainWidgetWindow;
    private Parent mainWidgetParent;
    private WindowLoader windowLoader;

    public InstallerWindow(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public Scene getScene() {
        return main;
    }

    public void setScene(Scene main) {
        this.main = main;
    }

    @PostConstruct
    public void init() {
        this.windowLoader = new WindowLoader(resource);
    }

    @Override
    public void loadBean() {
        mainWidgetWindow = getWindowLoader().loadFxmlFile();
        mainWidgetWindow.setControllerFactory(aClass -> applicationContext.getBean(aClass));
        mainWidgetParent = getParent(mainWidgetWindow);
        main = new Scene(mainWidgetParent, 640, 400);
    }

    public WindowLoader getWindowLoader() {
        return windowLoader;
    }
}
