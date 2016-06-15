package com.sms.core;

import com.sms.core.common.Promise;
import com.sms.core.common.React;
import com.sms.core.common.TwoTrack;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutionException;

public class DeferredResultProvider {

    public static <T> DeferredResult<ResponseEntity<T>> createDeferredResultTwoTrack(final Promise<TwoTrack<T>> task,
                                                                                     final HttpStatus httpStatus) {
        final DeferredResult<ResponseEntity<T>> deferredResult = new DeferredResult<>();
        task.success((t) -> {
            t.onSuccess(v -> deferredResult.setResult(new ResponseEntity<>(v, httpStatus)));
            t.onFailure(e -> deferredResult.setErrorResult(e));
        }).failure(deferredResult::setErrorResult);
        return deferredResult;

    }

    public static <T> DeferredResult<ResponseEntity<T>> createDeferredResult(final Promise<T> task,
                                                                             final HttpStatus httpStatus) {
        return createDeferredResultTwoTrack(React.of(task).then(TwoTrack::of).getPromise(), httpStatus);
    }


    public static <T> DeferredResult<ResponseEntity<T>> createDeferredResultTwoTrackRE(final Promise<TwoTrack<ResponseEntity<T>>> task) {
        final DeferredResult<ResponseEntity<T>> deferredResult = new DeferredResult<>();
        task.success(t -> {
            t.onSuccess(v -> deferredResult.setResult(v));
            t.onFailure(e -> deferredResult.setErrorResult(e));
        }).failure(deferredResult::setErrorResult);
        return deferredResult;

    }

    public static <T> DeferredResult<ResponseEntity<T>> createDeferredResultRE(final Promise<ResponseEntity<T>> task) {
        return createDeferredResultTwoTrackRE(React.of(task).then(TwoTrack::of).getPromise());
    }
}
