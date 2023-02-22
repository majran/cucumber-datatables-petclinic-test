package org.example.tests;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Properties;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Objects.isNull;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertiesReader {

    @SneakyThrows
    public static void loadConfig() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        Properties config = new Properties();
        config.load(loader.getResourceAsStream("test.properties"));
        config.load(loader.getResourceAsStream(format("test-%s.properties", getEnvName())));
        config.forEach((key, value) -> {
            if (Objects.isNull(System.getProperty(valueOf(key)))) {
                System.setProperty((String) key, (String) value);
            }
        });
    }

    private static String getEnvName() {
        String envProperty = System.getProperty("env");
        log.info("Environment passed [{}]", envProperty);
        return isNull(envProperty) || envProperty.isBlank() ? "dev" : envProperty;
    }

}