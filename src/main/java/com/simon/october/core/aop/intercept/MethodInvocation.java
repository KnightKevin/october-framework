package com.simon.october.core.aop.intercept;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

@Getter
@AllArgsConstructor
public class MethodInvocation {
    private final Object targetObject;
    private final Method targetMethod;
    private final Object[] args;

    public Object proceed() {
        // 执行某个目标对象的目标方法+参数
        return null;
    }
}
