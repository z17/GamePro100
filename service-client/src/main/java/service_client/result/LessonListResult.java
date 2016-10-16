package service_client.result;

import lombok.NoArgsConstructor;
import service_client.data.Lesson;

import java.util.ArrayList;

@NoArgsConstructor
public class LessonListResult extends Result<ArrayList<Lesson>> {
    public LessonListResult(String message, ArrayList<Lesson> data) {
        super(message, data);
    }
}
