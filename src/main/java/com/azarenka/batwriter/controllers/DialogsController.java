package com.azarenka.batwriter.controllers;

import com.azarenka.batwriter.SceneChanger;
import com.azarenka.batwriter.api.DialogsChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;

@Component
public class DialogsController extends SceneChanger implements DialogsChooser{

    public DialogsController(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public File showDialog(String path, boolean isFolder) {
        if (isFolder) {
            return  choiceFolder(path);
        } else {
            return choiceFile(path);
        }
    }

    private File choiceFile(String path) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(path));
        chooser.setTitle("Choose file for new command");
        final File file = chooser.showOpenDialog(stage);
        return Objects.nonNull(file) ? file : new File("namelessfile.bat");
    }

    private File choiceFolder(String path) {
        DirectoryChooser chooser = new DirectoryChooser();
        File file = chooser.showDialog(stage);
        chooser.setInitialDirectory(new File(path));
        return Objects.nonNull(file) ? file : new File("temp");
    }
}
