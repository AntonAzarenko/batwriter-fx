package com.azarenka.batwriter;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javafx.scene.Scene;

@Component
public class SceneChanger extends StageInitializer {

    public SceneChanger(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public void setNewScene(Scene scene) {
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
