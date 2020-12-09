package com.simon.october.core.config;

import com.simon.october.core.config.resource.yaml.YamlResourceLoader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Slf4j
public class ConfigurationManager implements Configuration {
    private static final String YML_FILE_EXTENSION = ".yml";

    private final Configuration configuration;

    public ConfigurationManager(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public int getInt(String id) {
        return configuration.getInt(id);
    }

    @Override
    public String getString(String id) {
        return configuration.getString(id);
    }

    @Override
    public Boolean getBoolean(String id) {
        return configuration.getBoolean(id);
    }

    @Override
    public void loadResources(List<Path> resourcePaths) {
        try {
            for (Path p : resourcePaths) {
                String fileName = p.getFileName().toString();
                if (fileName.endsWith(YML_FILE_EXTENSION)) {
                    ResourceLoader resourceLoader = new YamlResourceLoader();
                    configuration.putAll(resourceLoader.loadResource(p));
                }
            }
        } catch (IOException e) {
            log.error("not load resource");
            System.exit(-1);
        }
    }

    @Override
    public void putAll(Map<String, String> map) {

    }

    @Override
    public void put(String k, String v) {

    }
}
