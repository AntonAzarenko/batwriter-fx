package com.azarenka.batwriter;

import com.azarenka.batwriter.util.PropertiesLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public abstract class CommonWindowsWidget {

    @Autowired
    private PropertiesLoader propertiesLoader;

    protected ApplicationContext applicationContext;

    public CommonWindowsWidget(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public abstract void loadBean();

    protected Parent getParent(FXMLLoader loader) {
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    protected double getWidth() {
        return Double.parseDouble(propertiesLoader.getProperties("application.setting.resolution.width"));
    }

    protected double getHeight() {
        return Double.parseDouble(propertiesLoader.getProperties("application.setting.resolution.height"));
    }
}
