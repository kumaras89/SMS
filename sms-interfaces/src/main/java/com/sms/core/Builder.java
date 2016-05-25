package com.sms.core;

import static org.springframework.util.ReflectionUtils.*;
import static org.springframework.beans.BeanUtils.*;

import com.sms.core.admin.User;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by Ganesan on 25/05/16.
 */
public class Builder<T> {


    private static final FieldFilter nonStaticFilter = f-> !Modifier.isStatic(f.getModifiers());

    private Class<T> clazz;
    private T proxyInstance;
    private Map<String, Optional> properties;
    private MethodInterceptorImpl methodInterceptor;

    public Builder(Class<T> clazz) {
        this.clazz = clazz;
        this.properties = new HashMap<>();
        doWithFields(clazz, field -> properties.put(field.getName(), Optional.empty()), nonStaticFilter);
        this.methodInterceptor = new MethodInterceptorImpl();
        this.proxyInstance = getProxyInstance(clazz, methodInterceptor);

    }

    public static <T> Builder<T> of(Class<T> clazz) {
        return new Builder<>(clazz);
    }


    public <V> PropertyAccessor<T, V> on(Function<T, V> getter) {
        getter.apply(proxyInstance);
        String property = methodInterceptor.getLastAccessedProperty();
        return new PropertyAccessor<>(this, property);
    }


    public static final class PropertyAccessor<T, V> {

        private Builder<T> builder;
        private String propertyName;

        private PropertyAccessor(Builder<T> builder, String propertyName) {
            this.builder = builder;
            this.propertyName = propertyName;
        }

        public Optional<V> get() {
            return builder.properties.get(propertyName);
        }

        public Builder<T> set(V val) {
            builder.properties.put(propertyName, Optional.ofNullable(val));
            return builder;
        }

    }


    public T build() {
        T instance = instantiate(clazz);
        doWithFields(clazz, field -> properties.computeIfPresent(field.getName(), (key, val) -> {
                makeAccessible(field);
                setField(field, instance, val.orElse(null));
                return val;
            }), nonStaticFilter);
        return instance;
    }

    private static class MethodInterceptorImpl implements MethodInterceptor {

        private String lastAccessedProperty;


        public Object intercept(Object obj, Method method, Object[] args,
                                MethodProxy proxy) throws Throwable {
            Optional<String> methodName = Optional.of(method.getName());
            lastAccessedProperty = Optional.ofNullable(methodName.filter(m -> m.startsWith("get"))
                    .filter(m -> m.length() > 3)
                    .map(m -> m.substring(3))
                    .orElse(
                            methodName
                                    .filter(m -> m.startsWith("is"))
                                    .filter(m -> m.length() > 2)
                                    .map(m -> m.substring(2))
                                    .orElse(null)

                    )).map(m -> m.substring(0, 1).toLowerCase() + m.substring(1))
                    .orElse(lastAccessedProperty);
            return null;
        }

        public String getLastAccessedProperty() {
            return lastAccessedProperty;
        }
    }

    private static  <T> T getProxyInstance(Class<T> clazz, MethodInterceptor methodInterceptor) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(methodInterceptor);
        enhancer.setSuperclass(clazz);
        return (T)enhancer.create();
    }


}
