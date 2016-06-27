package com.sms.core.common;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Ganesan on 26/06/16.
 */
public class FList<T> {

    public final Collection<T> elements;

    public FList(Collection<T> elements) {
        this.elements = elements;
    }

    public static <T> FList<T> of(Collection<T> element) {
        return new FList<>(element);
    }

    public <U> FList<U> map(Function<T, U> mapper) {
        return new FList<>(FunctionUtils.asList(mapper).apply(elements));
    }

    public <U> U foldLeft(U startWith, BiFunction<U, T, U> adder) {
        return elements.stream().reduce(startWith, adder, (l, r) -> l);
    }

    public <A, R> R get(Collector<? super T, A, R> collector) {
        return elements.stream().collect(collector);
    }

    public List<T> get() {
        return get(Collectors.toList());
    }

    public <U> U reduce(Function<Collection<T>, U> reducer) {
        return reducer.apply(elements);
    }


}
