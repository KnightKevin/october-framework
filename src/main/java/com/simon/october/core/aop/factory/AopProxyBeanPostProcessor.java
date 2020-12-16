package com.simon.october.core.aop.factory;

import com.simon.october.core.aop.intercept.BeanPostProcessor;
import com.simon.october.core.aop.intercept.CglibAopProxyBeanPostProcessor;
import com.simon.october.core.aop.intercept.JdkAopProxyBeanPostProcessor;

public class AopProxyBeanPostProcessor {
    // think 去理解吧
    public static BeanPostProcessor get(Class<?> beanClass) {
        if (beanClass.isInterface() || beanClass.getInterfaces().length > 0) {
            return new JdkAopProxyBeanPostProcessor();
        } else {
            return new CglibAopProxyBeanPostProcessor();
        }
    }
}
