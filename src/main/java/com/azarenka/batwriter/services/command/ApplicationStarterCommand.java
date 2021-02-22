package com.azarenka.batwriter.services.command;

import com.azarenka.batwriter.api.ICommand;

import java.util.stream.IntStream;

/**
 * Description
 * <p>
 * (c) ant-azarenko@mail.ru 2020
 * </p>
 *
 * @author Anton Azarenka
 * Date 03.10.2020
 */
public class ApplicationStarterCommand implements ICommand {

    private static final String DEFAULT_DESCRIPTION = "Running...";

    @Override
    public String getCommand(String path, String... vars) {
        return buildCommand(path, vars);
    }

    private String buildCommand(String path, String... var) {
        StringBuilder builder = new StringBuilder();
        builder.append(getDescription(var)).append("\n");
        return builder.append("@ start ")
            .append(path)
            .toString();
    }

    private String getDescription(String... var) {
        StringBuilder builder = new StringBuilder();
        builder.append("@echo ");
        if (var.length > 0) {
            IntStream.range(0, var.length).forEach(i -> {
                builder.append(var[i]).append(" ");
            });
        } else {
            builder.append(DEFAULT_DESCRIPTION);
        }
        return builder.toString();
    }
}
