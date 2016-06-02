package com.sms.core.common;

import java.util.Optional;
import java.util.function.Consumer;

public class OptionalExt<T> {

    private final Optional<T> value;

    private OptionalExt(final Optional<T> theValue) {
        this.value = theValue;
    }

    public static <T> OptionalExt<T> of(final Optional<T> theValue) {
        return new OptionalExt<>(theValue);
    }


    public static <T> OptionalExt<T> of(final T theValue) {
        return new OptionalExt<>(Optional.ofNullable(theValue));
    }


    public void ifPresentOrElse(final Consumer<T> action, final Runnable emptyAction) {
        if (value.isPresent()) {
            action.accept(value.get());
        } else {
            emptyAction.run();
        }

    }

}
