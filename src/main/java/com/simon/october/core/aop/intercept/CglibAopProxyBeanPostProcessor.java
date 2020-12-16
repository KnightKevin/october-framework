package com.simon.october.core.aop.intercept;

import com.simon.october.core.aop.proxy.CglibAspectProxy;

public class CglibAopProxyBeanPostProcessor extends AbstractAopProxyBeanPostProcessor {
    @Override
    public Object wrapBean(Object target, Interceptor interceptor) {
        return CglibAspectProxy.wrap(target, interceptor);
    }
}
