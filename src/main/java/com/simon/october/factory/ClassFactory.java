package com.simon.october.factory;

import com.simon.october.annotation.Component;
import com.simon.october.annotation.RestController;
import com.simon.october.annotation.aop.Aspect;
import com.simon.october.common.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ClassFactory {
    public static final Map<Class<? extends Annotation>, Set<Class<?>>> CLASSES = new ConcurrentHashMap<>();

    public static void loadClass(String[] packageNames) {
        Set<Class<?>> restControllers = ReflectionUtil.scanAnnotatedClass(packageNames, RestController.class);
        Set<Class<?>> components = ReflectionUtil.scanAnnotatedClass(packageNames, Component.class);
        Set<Class<?>> aspects = ReflectionUtil.scanAnnotatedClass(packageNames, Aspect.class);

        CLASSES.put(RestController.class, restControllers);
        CLASSES.put(Component.class, components);
        CLASSES.put(Aspect.class, aspects);
    }
}
