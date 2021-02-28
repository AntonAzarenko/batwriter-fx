package com.azarenka.batwriter.api;

/**
 * Description
 * <p>
 * (c) ant-azarenko@mail.ru 2020
 * </p>
 *
 * @author Anton Azarenka
 * Date 03.10.2020
 */
public interface ICommand {

    String getCommand(String alias, String command, String path, String... vars);
}
