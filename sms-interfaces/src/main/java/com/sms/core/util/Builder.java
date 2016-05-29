package com.sms.core.util;

import static org.springframework.util.ReflectionUtils.*;
import static org.springframework.beans.BeanUtils.*;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by Ganesan on 25/05/16.
 */
public final class Builder<T> {


    private static final FieldFilter nonStaticFilter = f-> !Modifier.isStatic(f.getModifiers());

    private final Class<T> clazz;
    private final T proxyInstance;
    private final Map<String, Optional> properties;
    private final MethodInterceptorImpl methodInterceptor;

    public Builder(final Class<T> clazz, final Function<Field, Optional> fieldValProvider) {
        this.clazz = clazz;
        this.properties = new HashMap<>();
        doWithFields(clazz, field -> properties.put(field.getName(), fieldValProvider.apply(field)), nonStaticFilter);
        this.methodInterceptor = new MethodInterceptorImpl();
        this.proxyInstance = getProxyInstance(clazz, methodInterceptor);

    }

    public static <T> Builder<T> of(final Class<T> clazz) {
        return new Builder<>(clazz, field -> Optional.empty() );
    }

    public static <T> Builder<T> of(final Class<T> clazz, final T instance) {
        return new Builder<>(clazz, field -> { field.setAccessible(true); return Optional.ofNullable(getField(field, instance)); } );
    }


    public <V> PropertyAccessor<T, V> on(final Function<T, V> getter) {
        getter.apply(proxyInstance);
        String property = methodInterceptor.getLastAccessedProperty();
        return new PropertyAccessor<>(this, property);
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


    private static  <T> T getProxyInstance(final Class<T> clazz, final MethodInterceptor methodInterceptor) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(methodInterceptor);
        enhancer.setSuperclass(clazz);
        return (T)enhancer.create();
    }

    private static class MethodInterceptorImpl implements MethodInterceptor {

        private String lastAccessedProperty;


        public Object intercept(final Object obj, final Method method, final Object[] args,
                                MethodProxy proxy) throws Throwable {
            Optional<String> methodName = Optional.of(method.getName());
            lastAccessedProperty = Optional.ofNullable(methodName.filter(m -> m.startsWith("get"))
                    .filter(m -> m.length() > 3)
                    .map(m -> m.substring(3))
                    .orElse(methodName
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

}
