package com.simon.october.common.util;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

@Slf4j
public class ReflectionUtil {

    /**
     * scan the classes marked by the specified annotation in the specified package
     */
    public static Set<Class<?>> scanAnnotatedClass(String[] packageNames, Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(packageNames, new TypeAnnotationsScanner());
        Set<Class<?>> annotatedClass = reflections.getTypesAnnotatedWith(annotation, true);
        log.info("the number of class with annotation @{} is {}", annotation.getSimpleName(), annotatedClass.size());
        return annotatedClass;
    }

    /**
     * get the implementation of the interface
     */
    public static <T> Set<Class<? extends T>> scanSubClass(String[] packageNames, Class<T> clazz) {
        Reflections reflections = new Reflections(packageNames, new SubTypesScanner());
        Set<Class<? extends T>> classes = reflections.getSubTypesOf(clazz);
        log.info("the number of sub class of {} is {}", clazz.getSimpleName(), classes.size());
        return classes;
    }

    public static void setField(Object o, Field field, Object fieldInstance) {
        field.setAccessible(true);
        try {
            field.set(o, fieldInstance);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }
}
