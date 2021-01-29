package com.azarenka.batwriter.services.env;

import com.azarenka.batwriter.api.IEnvironmentSetter;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ESetterI implements IEnvironmentSetter {

    @Override
    public void setEnvironment(Map<String, String> vars) {

    }
}
