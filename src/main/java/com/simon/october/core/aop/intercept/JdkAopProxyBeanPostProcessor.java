package com.simon.october.core.aop.intercept;

import com.simon.october.core.aop.proxy.JdkAspectProxy;

public class JdkAopProxyBeanPostProcessor extends AbstractAopProxyBeanPostProcessor {
    @Override
    public Object wrapBean(Object target, Interceptor interceptor) {
        return JdkAspectProxy.wrap(target, interceptor);
    }
}
