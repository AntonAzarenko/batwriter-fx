package com.azarenka.batwriter.controllers;

import com.azarenka.batwriter.SceneChanger;
import com.azarenka.batwriter.windows.InstallerWindow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

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
public class GreetingController {
    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private InstallerWindow installerWindow;
    @Autowired
    private InstallerController installerController;
    @FXML
    public TextArea textArea;
    @FXML
    private Button nextButton;
    @FXML
    public Button close;

    public void initialize() {
        textArea.setText(
            "\tПриветстую тебя пользователь. Ты  запустил мастера настройки" +
            "переменных окружения и .bat файлов.  Программма создаст на диске" +
            "С: основной командный файл под именем - 'c'.  Автоматически была" +
            "установлена системная переменная-JAVA_HOME.\n" +
            "\tТакже  вам  будет  доступна  функция  добавления  любых  системных " +
            "переменных в автоматическом режиме при минимальных настройках" +
            "программы. Вы сможете использовать  основной  командный файл." +
            " Команды вводятся в консоли в виде'с<пробел><ваша команда>'");
    }

    public void next() {
        sceneChanger.setNewScene(installerWindow.getScene());
    }

    public void close() {
        System.exit(0);
    }

    @PostConstruct
    private void init() {
        installerWindow.loadBean();
    }
}
