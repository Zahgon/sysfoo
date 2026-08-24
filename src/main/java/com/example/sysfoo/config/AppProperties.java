package com.example.sysfoo.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "app")
public interface AppProperties {
    String version();
}
