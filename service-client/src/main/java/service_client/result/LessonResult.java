package service_client.result;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import service_client.data.Lesson;

@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class LessonResult extends Result<Lesson> {
}
