package com.simon.october.core.config;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface Configuration {
    String APPLICATION_NAME = "application";

    int getInt(String id);

    String getString(String id);

    Boolean getBoolean(String id);

    default void loadResources(List<Path> resourcePaths) {

    }

    void putAll(Map<String, String> map);

    void put(String k, String v);
}
