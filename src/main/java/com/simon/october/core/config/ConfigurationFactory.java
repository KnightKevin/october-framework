package com.simon.october.core.config;

/**
 * 构造一个全局唯一的默认配置
 */
// think 不知道为什么要这么写，感觉怪怪的
public class ConfigurationFactory {
    public static Configuration getConfig() {
        return SingleConfigurationHolder.INSTANCE;
    }

    private static class SingleConfigurationHolder {
        private static final Configuration INSTANCE = buildConfiguration();

        private static Configuration buildConfiguration() {
            return new DefaultConfiguration();
        }
    }
}
