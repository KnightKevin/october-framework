package com.simon.october.core.aop.intercept;

public abstract class Interceptor {
    private int order = -1;

    public boolean supports(Object bean) {
        return false;
    }

    public abstract Object intercept(MethodInvocation methodInvocation);

}
