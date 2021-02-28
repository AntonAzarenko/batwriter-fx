package com.azarenka.batwriter;

import com.azarenka.batwriter.Main.StageEvent;
import com.azarenka.batwriter.windows.GreetingWindow;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Author Anton Azarenka
 */
@Component
public abstract class StageInitializer implements ApplicationListener<StageEvent> {

    @Autowired
    protected GreetingWindow greetingWindow;
    private ApplicationContext applicationContext;
    protected static Stage stage;

    public StageInitializer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageEvent stageEvent) {
        stage = stageEvent.getStage();
        greetingWindow.loadBean();
        setNewScene(greetingWindow.getScene());
        stage.setTitle("BatWriter");
        stage.setIconified(true);
        stage.show();
    }

    /**
     * Sets new scene.
     *
     * @param scene scene
     */
    public abstract void setNewScene(Scene scene);

}
