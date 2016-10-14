package service_client.result;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.ToString;
import service_client.data.Lesson;
import service_client.data.User;

@JsonSubTypes({
        @JsonSubTypes.Type(User.class),
        @JsonSubTypes.Type(Lesson.class)
})
@ToString
@Data
public abstract class Result<T> {
    protected String message;
    protected T data;
}
