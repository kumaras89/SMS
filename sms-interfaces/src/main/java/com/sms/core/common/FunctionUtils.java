package com.sms.core.common;

import java.util.List;
import java.util.Optional;
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





}
