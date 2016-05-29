package com.sms.core.util;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Created by Ganesan on 29/05/16.
 */
public final class React<T> {

    private CompletableFuture<T> completableFuture;

    private React(CompletableFuture<T> completableFuture) {
        this.completableFuture = completableFuture;
    }

    public static <T> React<T> of(T t) {
        return new React<>(completedFuture(t));
    }

    public static <T> React<T> of(React<T> t) {
        return new React<>(t.get());
    }

    public static <T> React<T> of(Supplier<T> t) {
        return new React<>(supplyAsync(t));
    }

    public static <T> React<T> of(CompletableFuture<T> t) {
        return new React<>(t);
    }

    public <U> React<U> then(Function<T, U> function) {
         return thenWithCF(t -> supplyAsync(() -> function.apply(t)));
    }

    public React<T> thenWithVoid(Consumer<T> function) {
        return then(t -> { function.accept(t); return t; });
    }

    public <U> React<U> thenWithCF(Function<T, CompletableFuture<U>> function) {
        return new React<>(completableFuture.thenComposeAsync(t -> function.apply(t)));
    }

    public <U> React<U> thenWithReact(Function<T, React<U>> function) {
        return thenWithCF(t -> function.apply(t).get());
    }


    public CompletableFuture<T> get() {
        return completableFuture;
    }

    public Promise<T> getPromise() {
        return Promise.of(completableFuture);
    }

}
