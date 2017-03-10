package lesson.repository;

import lesson.entity.LessonEntity;
import lesson.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
    List<TaskEntity> getListByLesson(LessonEntity lesson);
    List<TaskEntity> findByLessonId(Long lessonId);
}
