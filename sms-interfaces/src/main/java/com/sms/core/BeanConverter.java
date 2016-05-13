package com.sms.core;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.function.Supplier;

/**
 * <replace with description of each class>.
 *
 * @author snagasubramaniyam on 12-05-2016 14:49.
 */

public class BeanConverter<S, T> implements Converter<S, T> {

    public Supplier<T> destSupplier;

    private BeanConverter(Supplier<T> destSupplier) {
        this.destSupplier = destSupplier;
    }

    public static <S, T> Converter<S, T> of(Supplier<T> destSupplier) {
        return new BeanConverter<>(destSupplier);
    }

    @Override
    public T convert(S source) {
        T dest = destSupplier.get();
        BeanUtils.copyProperties(source, dest);
        return dest;
    }
}
