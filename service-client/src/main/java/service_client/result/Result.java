package service_client.result;

import lombok.Data;

@Data
public abstract class Result<T> {
    protected String message;
    protected T data;
}
