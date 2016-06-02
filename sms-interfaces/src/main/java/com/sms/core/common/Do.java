package com.sms.core.common;

import java.util.function.Consumer;
import java.util.function.Function;


/**
 * A monad for Synchronous execution of functions
 * Created by Ganesan on 02/06/16.
 */
public class Do<T, R> {

    private Function<T, R> function;

    private Do(Function<T, R> theFunction) {
        this.function = theFunction;
    }

    public static <T,R> Do<T,R> of(Function<T,R> f) {
        return new Do<>(f);
    }
    public static <T,R> Do<T,R> of(R r) {
        return of((T a) -> r);
    }

    public <U> Do<T, U> then(Function<R, U> mapper) {
        return of(function.andThen(mapper));
    }

    public Do<T, T> thenSame(Consumer<R> mapper) {
        return of(t-> function.andThen(r ->{ mapper.accept(r); return t; } ).apply(t) );
    }

    public Do<T, Void> thenVoid(Consumer<R> mapper) {
        return of(t -> function.andThen(r -> { mapper.accept(r); return (Void) null; }).apply(t));
    }

    public <U> Do<T,U> thenFlat(Function<T,Do<R,U>> doNextFunc) {
        return of(t-> function.andThen(doNextFunc.apply(t).function).apply(t) );
    }


    public Function<T,R> get() {
        return function;
    }
    public R get(T input) {
        return function.apply(input);
    }

    public R getEmmpty() {
        return function.apply(null);
    }

}
