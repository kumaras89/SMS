package com.sms.core.common;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static org.springframework.beans.BeanUtils.instantiate;
import static org.springframework.util.ReflectionUtils.*;


public class Builder<T> {

    private static final ReflectionUtils.FieldFilter NON_STATIC_FILTER_FILTER = f -> !Modifier.isStatic(f.getModifiers());

    private final Class<T> clazz;
    private final T proxyInstance;
    @SuppressWarnings("rawtypes")
    private final Map<String, Optional> properties;
    private final MethodInterceptorImpl methodInterceptor;

    private Builder(final Class<T> clazz, final Function<Field, Object> callBack) {
        this.clazz = clazz;
        this.properties = new HashMap<>();
        doWithFields(clazz, field -> properties.put(field.getName(), Optional.ofNullable(callBack.apply(field))),
            NON_STATIC_FILTER_FILTER);
        this.methodInterceptor = new MethodInterceptorImpl();
        this.proxyInstance = getProxyInstance(clazz, methodInterceptor);
    }


    public static <T> Builder<T> of(final Class<T> theClazz) {
        return new Builder<>(theClazz, field -> null);
    }

    public static <T> Builder<T> of(final Class<T> theClazz,final T instance) {
        return new Builder<>(theClazz, field -> {
            field.setAccessible(true);
            return getField(field, instance);
        });
    }

    public <V> PropertyAccessor<T, V> on(final Function<T, V> getter) {
        getter.apply(proxyInstance);
        final String property = methodInterceptor.getLastAccessedProperty();
        return new PropertyAccessor<>(this, property);
    }

    public <V> Builder<T> with(final Function<T, V> getter, final V val) {
        on(getter).set(val);
        return this;
    }


    public static final class PropertyAccessor<T, V> {

        private final Builder<T> builder;
        private final String propertyName;

        private PropertyAccessor(final Builder<T> builder,final String propertyName) {
            this.builder = builder;
            this.propertyName = propertyName;
        }

        @SuppressWarnings("unchecked")
        public Optional<V> get() {
            return builder.properties.get(propertyName);
        }

        public Builder<T> set(final V val) {
            builder.properties.put(propertyName, Optional.ofNullable(val));
            return builder;
        }

    }

    @SuppressWarnings("unchecked")
    public T build() {
        final T instance = instantiate(clazz);
        doWithFields(clazz, field -> properties.computeIfPresent(field.getName(),
            (key, val) -> {
                makeAccessible(field);
                setField(field, instance, val.orElse(null));
                return val;
            }),
            NON_STATIC_FILTER_FILTER);
        return instance;
    }

    private static class MethodInterceptorImpl implements MethodInterceptor {

        private String lastAccessedProperty;

        @Override
        public Object intercept(final Object obj,final Method method,final Object[] args,
            final MethodProxy proxy)
            throws Throwable {
            final Optional<String> methodName = Optional.of(method.getName());
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

    @SuppressWarnings("unchecked")
    private static <T> T getProxyInstance(final Class<T> theClazz,final MethodInterceptor theMethodInterceptor) {
        final Enhancer enhancer = new Enhancer();
        enhancer.setCallback(theMethodInterceptor);
        enhancer.setSuperclass(theClazz);
        return (T) enhancer.create();
    }

}
