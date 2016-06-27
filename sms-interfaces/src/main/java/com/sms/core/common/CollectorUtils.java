package com.sms.core.common;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by Ganesan on 26/06/16.
 */
public class CollectorUtils {



    public static <E, T> Collector<E, T[], T> grouping(final Supplier<T> initialValueSupplier,
                                                       final Function<E, T> reducer,
                                                       final BiFunction<T, T, T> accumulator) {
        return Collector.of(() -> {
                    final T initialValue = initialValueSupplier.get();
                    @SuppressWarnings({"unchecked", "SuspiciousArrayCast"})
                    final T[] ar = (T[]) new Object[1];
                    ar[0] = initialValue;
                    return ar;
                },
                (T[] ar, E e) -> ar[0] = accumulator.apply(ar[0], reducer.apply(e)),
                (T[] a, T[] b) -> {
                    a[0] = accumulator.apply(a[0], b[0]);
                    return a;
                },
                (T[] ar) -> ar[0]);
    }

}
