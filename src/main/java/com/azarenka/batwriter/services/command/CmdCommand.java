package com.azarenka.batwriter.services.command;

import com.azarenka.batwriter.api.ICommand;

import java.util.stream.IntStream;

/**
 * Description
 * <p>
 * (c) ant-azarenko@mail.ru 2021
 * </p>
 *
 * Date 22.02.2021
 *
 * @author Anton Azarenka
 */
public class CmdCommand implements ICommand {

    private static final String DEFAULT_DESCRIPTION = "...";

    @Override
    public String getCommand(String alias, String command, String path, String... vars) {
        StringBuilder builder = new StringBuilder();
        builder.append("@echo ");
        if (vars.length > 0) {
            IntStream.range(0, vars.length).forEach(i -> {
                builder.append(vars[i]).append(" ");
            });
        } else {
            builder.append(DEFAULT_DESCRIPTION);
        }
        return builder.toString() + "\n" + command;
    }
}
