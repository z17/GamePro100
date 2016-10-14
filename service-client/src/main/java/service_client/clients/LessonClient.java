package service_client.clients;

import service_client.data.Lesson;
import service_client.data.LessonCreation;
import service_client.result.LessonListResult;
import service_client.result.LessonResult;

import java.util.List;

public class LessonClient extends Client {
    private static final String SERVICE_PATH = "/lesson-service";

    public Lesson getLesson(final Long id) {
        return get(SERVICE_PATH + "/lesson/" + id, LessonResult.class).getData();
    }

    public List<Lesson> getList() {
        return get(SERVICE_PATH + "/lesson/", LessonListResult.class).getData();
    }

    public Lesson add(final LessonCreation lessonCreation) {
        return post(SERVICE_PATH + "/lesson/add", lessonCreation, LessonResult.class).getData();
    }

    public Lesson update(final Lesson lesson) {
        return post(SERVICE_PATH + "/lesson/update", lesson, LessonResult.class).getData();
    }
}
