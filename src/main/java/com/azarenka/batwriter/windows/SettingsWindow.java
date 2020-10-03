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
public class SettingsWindow extends CommonWindowsWidget{


        @Value(value = "classpath:fxml/.batwriter-setting.fxml")
        private Resource resource;
        private Scene main;
        private FXMLLoader mainWidgetWindow;
        private Parent mainWidgetParent;
        private WindowLoader windowLoader;

        public SettingsWindow(ApplicationContext applicationContext) {
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
