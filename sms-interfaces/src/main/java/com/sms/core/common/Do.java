package com.sms.core.common;

import java.util.function.Consumer;
import java.util.function.Function;


/**
 * A monad for Synchronous execution of functions
 * Created by Ganesan on 02/06/16.
 */
public class Do<T, R> {

    private final Function<T, R> function;

    private Do(final Function<T, R> theFunction) {
        this.function = theFunction;
    }

    public static <T,R> Do<T,R> of(final Function<T,R> f) { return new Do<>(f);}

    public static <T,R> Do<T,R> of(final R r) {
        return of((T a) -> r);
    }

    public <U> Do<T, U> then(final Function<R, U> mapper) {
        return of(function.andThen(mapper));
    }

    public Do<T, T> thenSame(final Consumer<R> mapper) {
        return of(t-> function.andThen(r ->{ mapper.accept(r); return t; } ).apply(t) );
    }

    public Do<T, Void> thenVoid(final Consumer<R> mapper) {
        return of(t -> function.andThen(r -> { mapper.accept(r); return (Void) null; }).apply(t));
    }

    public <U> Do<T,U> thenFlat(final Function<T,Do<R,U>> doNextFunc) {
        return of(t-> function.andThen(doNextFunc.apply(t).function).apply(t) );
    }

    public Function<T,R> getF() {
        return function;
    }

    public R get(final T input) {
        return function.apply(input);
    }
    
    public R get() {
        return function.apply(null);
    }

}
