package com.simon.october.serialize;

public interface Serializer {
    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);
}
