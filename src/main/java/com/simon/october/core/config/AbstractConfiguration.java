package com.simon.october.core.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AbstractConfiguration implements Configuration {

    private static final Map<String, String> CONFIGURATION_CACHE = new ConcurrentHashMap<>(64);

    @Override
    public int getInt(String id) {
        String v = CONFIGURATION_CACHE.get(id);
        return Integer.parseInt(v);
    }

    @Override
    public String getString(String id) {
        return CONFIGURATION_CACHE.get(id);
    }

    @Override
    public Boolean getBoolean(String id) {
        String v = CONFIGURATION_CACHE.get(id);
        return Boolean.parseBoolean(v);
    }

    @Override
    public void putAll(Map<String, String> map) {
        CONFIGURATION_CACHE.putAll(map);
    }

    @Override
    public void put(String k, String v) {
        CONFIGURATION_CACHE.put(k, v);
    }
}
