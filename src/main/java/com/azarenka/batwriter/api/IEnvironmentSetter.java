package com.azarenka.batwriter.api;

import java.util.Map;

public interface IEnvironmentSetter {

    void setEnvironment(Map<String, String> vars);
}
