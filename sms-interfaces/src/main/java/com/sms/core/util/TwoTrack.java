package com.sms.core.util;

import java.util.function.Function;

/**
 * Created by Ganesan on 29/05/16.
 */
public interface TwoTrack<T> {

    static <T>  TwoTrack<T> of(T val) {
        return new SuccessTrack<>(val);
    }

    static <T>  TwoTrack<T> of(ErrorCode error) {
        return new FailureTrack<>(error);
    }

    T get();

    ErrorCode getErrorCode();

    boolean isSuccess() ;

    <R> TwoTrack<R> map(Function<T, R> function);

    class SuccessTrack<T> implements TwoTrack<T> {

        private final T val;

        private SuccessTrack(T val){
            this.val = val;
        }

        @Override
        public T get() {
            return val;
        }

        @Override
        public ErrorCode getErrorCode() {
            return null;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public <R> TwoTrack<R> map(Function<T, R> function) {
            return TwoTrack.of(function.apply(get()));
        }


    }

    class FailureTrack<T> implements TwoTrack<T> {

        private final ErrorCode errorCode;

        private FailureTrack(ErrorCode errorCode){
            this.errorCode = errorCode;
        }

        @Override
        public T get() {
            return null;
        }

        @Override
        public ErrorCode getErrorCode() {
            return errorCode;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public <R> TwoTrack<R> map(Function<T, R> function) {
            return TwoTrack.of(errorCode);
        }
    }


}
