package service_client.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    public String message;
    public T data;

    public static <T> Result<T> success(final T data) {
        return new Result<>(null, data);
    }

    public static <T> Result<T> error(final String message) {
        return new Result<>(message, null);
    }

    public static <T> Result<T> run(final Supplier<T> function ) {
        final T result = function.get();
        return Result.success(result);
    }
}