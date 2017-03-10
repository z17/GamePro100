package service_client.client;

import service_client.data.Lesson;
import service_client.data.request.LessonCreation;
import service_client.result.LessonListResult;
import service_client.result.LessonResult;

import java.util.List;

public class LessonClient extends Client {
    private static final String SERVICE_PATH = "/lesson-service/lesson/";

    LessonClient() {
        super(SERVICE_PATH);
    }

    public Lesson get(final Long id) {
        return get(id.toString(), LessonResult.class).getData();
    }

    public List<Lesson> getList() {
        return get("", LessonListResult.class).getData();
    }

    public Lesson add(final LessonCreation lessonCreation) {
        return post( "/add", lessonCreation, LessonResult.class).getData();
    }

    public Lesson update(final Lesson lesson) {
        return post("/update", lesson, LessonResult.class).getData();
    }
}
