package executor.request;

import lombok.Data;

@Data
public class SubmitRequest {
    private final Long taskId;
    private final String code;
}
