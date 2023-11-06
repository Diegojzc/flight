package com.tokioschool.flightapp.store.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;

@ConfigurationProperties(prefix="application.store")
public record StoreConfigurationProperties(Path basePath) {

    public Path getPath(String resourceName) {
        return Path.of(basePath.toString(), resourceName);
    }
}

