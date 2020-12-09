package com.simon.october.core.config.resource.yaml;

import com.simon.october.core.config.ResourceLoader;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class YamlResourceLoader implements ResourceLoader {

    /**
     * 将yml文件的内容，如下:
     * aaa:
     * bbb:
     * ccc: 123
     * 转成map:
     * aaa.bbb.ccc->123
     */
    @Override
    public Map<String, String> loadResource(Path path) throws IOException {
        Map<String, String> result = new HashMap<>();
        InputStream inputStream = null;
        Reader reader = null;
        try {
            Yaml yaml = new Yaml();
            inputStream = Files.newInputStream(path);
            reader = new InputStreamReader(inputStream);
            Map<String, Object> map = asMap(yaml.load(reader));
            formatMap(result, map, null);
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }

            if (null != reader) {
                reader.close();
            }
        }

        return result;
    }

    /**
     * 格式化map，变成Map<String, String>格式
     */
    private void formatMap(Map<String, String> result, Map<String, Object> source, String lastKey) {
        source.forEach((k, v) -> {
            if (null != lastKey && !lastKey.isEmpty()) {
                k = lastKey + "." + k;
            }

            if (v instanceof String) {
                result.put(k, (String) v);
            } else if (v instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) v;
                formatMap(result, map, k);
            } else {
                result.put(k, null == v ? "" : String.valueOf(v));
            }
        });
    }

    private Map<String, Object> asMap(Object object) {
        Map<String, Object> result = new HashMap<>();

        Map<Object, Object> map = (Map<Object, Object>) object;
        map.forEach((k, v) -> {
            if (v instanceof Map) {
                asMap(v);
            }
            result.put(k.toString(), v);
        });
        return result;
    }
}
