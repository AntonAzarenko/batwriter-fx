package com.azarenka.batwriter.services.command.infrastructure;

import com.azarenka.batwriter.api.ICommand;
import com.azarenka.batwriter.services.command.SystemVariableCommand;
import com.azarenka.batwriter.util.PropertiesLoader;

/**
 * Description
 * <p>
 * (c) ant-azarenko@mail.ru 2020
 * </p>
 *
 * @author Anton Azarenka
 * Date 03.10.2020
 */
public class SystemVariableInitializer implements ICommandCreator {

    PropertiesLoader propertiesLoader;

    public SystemVariableInitializer(PropertiesLoader propertiesLoader) {
        this.propertiesLoader = propertiesLoader;
    }

    @Override
    public ICommand initCreator() {
        return new SystemVariableCommand(propertiesLoader);
    }
}
