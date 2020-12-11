package com.simon.october.core.aop.proxy;

import com.simon.october.core.aop.intercept.Interceptor;
import com.simon.october.core.aop.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkAspectProxy implements InvocationHandler {
    private final Object target;
    private final Interceptor interceptor;

    public JdkAspectProxy(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        MethodInvocation methodInvocation = new MethodInvocation(target, method, args);
        return interceptor.intercept(methodInvocation);
    }
}
