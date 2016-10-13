package lesson.repository;

import lesson.entity.LessonEntity;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<LessonEntity, Long> {
}
