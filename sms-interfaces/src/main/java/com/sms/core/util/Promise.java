package com.sms.core.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/**
 * Created by Ganesan on 29/05/16.
 */
public final class Promise<T> {

    private final CompletableFuture<T> future;

    private Promise(CompletableFuture<T> future) {
        this.future = future;
    }

    public static <T> Promise<T> of(CompletableFuture<T> future) {
        return new Promise<>(future);
    }

    public Promise<T> success(Consumer<T> success) {
        return then(success, e -> {});

    }

    public Promise<T> failure(Consumer<Throwable> failure) {
        return then(t -> {}, failure);
    }

    public Promise<T> then(Consumer<T> success, Consumer<Throwable> failure) {
        return Promise.of(future.whenCompleteAsync(
                (t,e) -> OptionalExt.of(e)
                    .ifPresentOrElse(failure, () -> success.accept(t))));
    }


}
