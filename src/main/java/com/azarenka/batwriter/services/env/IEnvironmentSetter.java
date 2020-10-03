package com.azarenka.batwriter.services.env;

import java.util.Map;

public interface IEnvironmentSetter {

    void setEnvironment(Map<String, String> vars);
}
