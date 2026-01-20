package anup8bit.com.ecommerce.async;

import anup8bit.com.ecommerce.dto.AsyncTaskResult;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public interface AsyncExecutor {
    <R>CompletableFuture<AsyncTaskResult<R>> executeAsync(
            Supplier<R> supplier
    );
}
