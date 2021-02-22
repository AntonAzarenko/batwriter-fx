package com.azarenka.batwriter.services.command.infrastructure;

import com.azarenka.batwriter.api.ICommand;
import com.azarenka.batwriter.services.command.ChangeDirCommand;

/**
 * Description
 * <p>
 * (c) ant-azarenko@mail.ru 2020
 * </p>
 *
 * @author Anton Azarenka
 * Date 03.10.2020
 */
public class ChangeDirInitializer implements ICommandCreator {

    @Override
    public ICommand initCreator() {
        return new ChangeDirCommand();
    }
}
