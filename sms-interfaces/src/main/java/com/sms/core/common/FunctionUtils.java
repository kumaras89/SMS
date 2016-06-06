package com.sms.core.common;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionUtils {

    public static <T, R> Function<Optional<T>, Optional<R>> asOptional(final Function<T, R> function) {
        return t -> Optional.of(function.apply(t.get()));
    }

    public static <T, R> Function<List<T>, List<R>> asList(final Function<T, R> mapper) {
        return t -> t.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T, R> Function<TwoTrack<T>, TwoTrack<R>> asTwoTrack(final Function<T, R> function) {
        return t -> t.map(function);
    }

    public static <T> CompletableFuture<T> buildCompletableFutureFromListenableFuture(final ListenableFuture<T> listenableFuture) {
        CompletableFuture<T> completable = new CompletableFuture<T>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                boolean result = listenableFuture.cancel(mayInterruptIfRunning);
                super.cancel(mayInterruptIfRunning);
                return result;
            }
        };
        listenableFuture.addCallback(t -> completable.complete(t), e -> completable.completeExceptionally(e));
        return completable;
    }

}
