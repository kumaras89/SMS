package com.sms.core.common;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public final class Promise<T> {

    private final CompletableFuture<T> future;

    private Promise(final CompletableFuture<T> future) {
        this.future = future;
    }

    public static <T> Promise<T> of(final CompletableFuture<T> theFuture) {
        return new Promise<>(theFuture);
    }

    public Promise<T> success(final Consumer<T> success) {
        return then(success, e -> {
        });
    }

    public Promise<T> failure(final Consumer<Throwable> failure) {
        return then(t -> {
        }, failure);
    }

    public Promise<T> then(final Consumer<T> success, final Consumer<Throwable> failure) {
        return Promise.of(future.whenCompleteAsync((t, e) -> OptionalExt.of(e)
            .ifPresentOrElse(failure, () -> success.accept(t))));
    }

    public CompletableFuture<T> get() {
        return future;
    }

    public CompletableFuture<T> getFuture(){
        return future;
    }

}
