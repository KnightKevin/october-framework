package com.simon.october.core.aop.factory;

import com.simon.october.common.util.ReflectionUtil;
import com.simon.october.core.aop.intercept.Interceptor;
import com.simon.october.exception.CannotInitializeConstructorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InterceptorFactory {

    private static List<Interceptor> interceptors = new ArrayList<>();

    public static void loadInterceptors(String[] packageName) {
        Set<Class<? extends Interceptor>> interceptorSet = ReflectionUtil.scanSubClass(packageName, Interceptor.class);

        // todo 获取被@Aspect标记的类

        // todo 遍历所有拦截器类
        interceptorSet.forEach(i -> {
            try {
                interceptors.add(i.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new CannotInitializeConstructorException("not init constructor, the interceptor name : " + i.getSimpleName());
            }
        });

        // todo 添加Bean验证拦截器

        // todo 根据order为拦截器排序
    }

    public static List<Interceptor> getInterceptors() {
        return interceptors;
    }
}
