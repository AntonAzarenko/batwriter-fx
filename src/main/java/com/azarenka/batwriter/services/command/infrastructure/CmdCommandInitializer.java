package com.azarenka.batwriter.services.command.infrastructure;

import com.azarenka.batwriter.api.ICommand;
import com.azarenka.batwriter.services.command.CmdCommand;

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
public class CmdCommandInitializer implements ICommandCreator {

    @Override
    public ICommand initCreator() {
        return new CmdCommand();
    }
}
