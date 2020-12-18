package com.simon.october.serialize.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simon.october.serialize.Serializer;

public class JacksonSerializer implements Serializer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * java 对象转换成二进制
     *
     * @param object
     */
    @Override
    public byte[] serialize(Object object) {
        byte[] bytes = new byte[0];
        try {
            bytes = objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
