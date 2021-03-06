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
import javafx.util.Callback;

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
public class GreetingWindow extends CommonWindowsWidget {

    @Value(value = "classpath:fxml/.batwriter-greeting.fxml")
    private Resource resource;
    private Scene greetingScene;
    private WindowLoader windowLoader;

    public GreetingWindow(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public Scene getScene() {
        return greetingScene;
    }

    public void setScene(Scene main) {
        this.greetingScene = main;
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
        greetingScene = new Scene(mainWidgetParent, super.getWidth(), super.getHeight());
    }
}
