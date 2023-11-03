package com.tokioschool.flightapp.store.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = StoreConfigurationProperties.class)
public class StoreConfig {
}
