package service_client.data.request;

import lombok.Data;

@Data
public class SubmitRequest {
    private final Long taskId;
    private final String code;
}
