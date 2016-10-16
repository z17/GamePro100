package service_client.result;

import lombok.NoArgsConstructor;
import service_client.data.Lesson;

@NoArgsConstructor
public class LessonResult extends Result<Lesson> {
    public LessonResult(String message, Lesson data) {
        super(message, data);
    }
}
