package com.sms.core.util;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Ganesan on 29/05/16.
 */
public class FunctionComposer {

    public static <T, R> Function<Optional<T>, Optional<R>> asOptional(Function<T, R> function) {
        return t -> Optional.of(function.apply(t.get()));
    }

    public static <T, R> Function<List<T>, List<R>> asList(Function<T, R> mapper) {
        return t -> t.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T, R> Function<TwoTrack<T>, TwoTrack<R>> asTwoTrack(Function<T, R> function) {
        return t -> t.map(function);
    }




}
