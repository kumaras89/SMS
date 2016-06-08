package com.sms.core;

import com.sms.core.common.Promise;
import com.sms.core.common.React;
import com.sms.core.common.TwoTrack;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutionException;

public class DeferredResultProvider {

    public static <T> ResponseEntity<T> createDeferredResultTwoTrack(final Promise<TwoTrack<T>> task,
                                                                                     final HttpStatus httpStatus) {
//        final DeferredResult<ResponseEntity<T>> deferredResult = new DeferredResult<>();
//        task.success((t) -> {
//            t.onSuccess(v -> deferredResult.setResult(new ResponseEntity<>(v, httpStatus)));
//            t.onFailure(e -> deferredResult.setErrorResult(e));
//        }).failure(deferredResult::setErrorResult);
        try {
            return new ResponseEntity<>(task.get().get().get(), httpStatus);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> ResponseEntity<T> createDeferredResult(final Promise<T> task,
                                                                             final HttpStatus httpStatus) {
        return createDeferredResultTwoTrack(React.of(task).then(TwoTrack::of).getPromise(), httpStatus);
    }
}
