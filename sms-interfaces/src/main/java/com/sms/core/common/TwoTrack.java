package com.sms.core.common;

import java.util.function.Consumer;
import java.util.function.Function;

public interface TwoTrack<T> {

    static <T> TwoTrack<T> of(final T val) {
        return new SuccessTrack<>(val);
    }

    static <T> TwoTrack<T> of(final ErrorCodeAndParam error) {
        return new FailureTrack<>(error);
    }

    T get();

    ErrorCodeAndParam getErrorCode();

    boolean isSuccess();

    <R> TwoTrack<R> map(Function<T, R> function);

    void onSuccess(Consumer<T> success);
    void onFailure(Consumer<ErrorCodeAndParam> failure);

    class SuccessTrack<T> implements TwoTrack<T> {

        private final T val;

        private SuccessTrack(final T val) {
            this.val = val;
        }

        @Override
        public T get() {
            return val;
        }

        @Override
        public ErrorCodeAndParam getErrorCode() {
            return null;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public <R> TwoTrack<R> map(final Function<T, R> function) {
            return TwoTrack.of(function.apply(get()));
        }

        @Override
        public void onSuccess(final Consumer<T> success) {
            success.accept(val);
        }

        @Override
        public void onFailure(final Consumer<ErrorCodeAndParam> success) {

        }


    }

    class FailureTrack<T> implements TwoTrack<T> {

        private final ErrorCodeAndParam errorCode;

        private FailureTrack(final ErrorCodeAndParam errorCode) {
            this.errorCode = errorCode;
        }

        @Override
        public T get() {
            return null;
        }

        @Override
        public ErrorCodeAndParam getErrorCode() {
            return errorCode;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public <R> TwoTrack<R> map(final Function<T, R> function) {
            return TwoTrack.of(errorCode);
        }

        @Override
        public void onSuccess(final Consumer<T> success) {

        }

        @Override
        public void onFailure(final Consumer<ErrorCodeAndParam> failure) {
            failure.accept(errorCode);
        }
    }


}
