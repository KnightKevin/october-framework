package com.simon.october.core.ioc;

import com.simon.october.annotation.Autowired;
import com.simon.october.annotation.Value;
import com.simon.october.common.util.ReflectionUtil;
import com.simon.october.exception.CanNotDetermineTargetBeanException;
import com.simon.october.exception.InterfaceHaveNoImplementedClassException;

import java.lang.reflect.Field;
import java.util.Set;

public class AutowiredBeanInitialization {
    private final String[] packageNames;

    public AutowiredBeanInitialization(String[] packageNames) {
        this.packageNames = packageNames;
    }

    public void initialize(Object o) {
        // 获取bean的所有属性
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length <= 0) {
            return;
        }

        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                // 拿到该字段的的对象实例
                Object filedInstance = getBeanByAutowiredField(field);

                // todo aop 实现

                // 将该对象实例设置到bean当中
            }

            if (field.isAnnotationPresent(Value.class)) {

            }
        }
    }

    private Object getBeanByAutowiredField(Field field) {
        Class<?> clazz = field.getClass();
        // 获取该类所需要的bean name;
        String beanName = BeanHelper.getBeanName(clazz);

        Object o;

        if (clazz.isInterface()) {
            Set<Class<?>> subClassSet = ReflectionUtil.scanSubClass(packageNames, (Class<Object>) clazz);
            if (subClassSet.isEmpty()) {
                throw new InterfaceHaveNoImplementedClassException(clazz.getName() + " is interface and have no implemented sub class");
            }

            if (1 == subClassSet.size()) {
                Class<?> subClass = subClassSet.iterator().next();
                beanName = BeanHelper.getBeanName(subClass);
            }

            if (subClassSet.size() > 1) {
                // todo 若有多个候选bean的时候，须要指定某一个，友情提示@Qualifier就做这个事情的
            }
        }

        o = BeanFactory.BEANS.get(beanName);
        if (null == o) {
            throw new CanNotDetermineTargetBeanException("can not determine target bean of " + clazz.getName());
        }

        return o;
    }
}
