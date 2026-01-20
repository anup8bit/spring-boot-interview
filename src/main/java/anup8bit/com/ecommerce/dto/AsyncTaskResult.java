package anup8bit.com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AsyncTaskResult<T> {
    private final T data;
    private final Throwable error;

    public static <T> AsyncTaskResult<T> success(T data) {
        return new AsyncTaskResult<>(data, null);
    }

    public static <T> AsyncTaskResult<T> failure(Throwable error) {
        return new AsyncTaskResult<>(null, error);
    }

    public boolean isSuccess() {
        return error == null;
    }
}
