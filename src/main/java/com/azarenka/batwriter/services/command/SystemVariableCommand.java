package com.azarenka.batwriter.services.command;

import com.azarenka.batwriter.api.ICommand;
import com.azarenka.batwriter.util.PropertiesLoader;

/**
 * Class for create command to install system variable.
 * <p>
 * (c) ant-azarenko@mail.ru 2020
 * </p>
 *
 * @author Anton Azarenka
 * Date 03.10.2020
 */
public class SystemVariableCommand implements ICommand {

    private static final String APP_COM = " && ";
    private static final String INNER_QUOTE = "\"";
    private static final String SPACE = " ";
    private static final String BACKWARD_SLASH = "\\";
    private static final String EXIT = "exit";
    private static final String PATH = "%Path%";

    private final PropertiesLoader loader;
    private String start;
    private String command;

    public SystemVariableCommand(PropertiesLoader loader) {
        this.loader = loader;
    }

    @Override
    public String getCommand(String path, String... var) {
        initFields();
        return buildCommand(path, var);
    }

    private String buildCommand(String path, String... var) {
        StringBuilder builder = new StringBuilder();
        return builder.append(start)
            .append(SPACE)
            .append(INNER_QUOTE)
            .append(command)
            .append(SPACE)
            .append(var[0])
            .append(SPACE)
            .append("/M")
            .append(SPACE)
            .append(INNER_QUOTE)
            .append(path)
            .append(INNER_QUOTE)
            .append(APP_COM)
            .append(command)
            .append(SPACE)
            .append(var[1])
            .append(SPACE)
            .append("/M")
            .append(SPACE)
            .append(INNER_QUOTE)
            .append(path)
            .append(BACKWARD_SLASH)
            .append("bin")
            .append(";")
            .append(PATH)
            .append(INNER_QUOTE)
            .append(APP_COM)
            .append(EXIT)
            .append(INNER_QUOTE)
            .toString();
    }

    private void initFields() {
        start = loader.getProperties("application.command.start_console");
        command = loader.getProperties("application.command.system_env_var");
    }
}
