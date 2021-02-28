package com.azarenka.batwriter.windows;

import com.azarenka.batwriter.CommonWindowsWidget;
import com.azarenka.batwriter.WindowLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MainWindow extends CommonWindowsWidget {

    @Value(value = "classpath:fxml/.batwriter_v2.fxml")
    private Resource resource;
    private Scene main;
    private WindowLoader windowLoader;

    public MainWindow(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public Scene getScene() {
        return main;
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
        main = new Scene(mainWidgetParent, super.getWidth(), super.getHeight());
    }
}
