package com.azarenka.batwriter.util;

import com.azarenka.batwriter.domain.PropertiesApp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Component
public class PropertiesLoader {

    @Value(value = "${spring.application.properties}")
    private String path;

    @Value(value = "classpath:properties.ini")
    private Resource resource;

    public PropertiesApp loadProperties() {
        PropertiesApp propertiesApp = new PropertiesApp();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(Objects.requireNonNull(resource.getFilename())));
            propertiesApp.setPath(properties.getProperty("application.folder.path"));
            propertiesApp.setFileName(properties.getProperty("application.file.name"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesApp;
    }

    public String getProperties(String alias) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(Objects.requireNonNull(resource.getFilename())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(alias);
    }
}
