package lesson.service;

import lesson.entity.LessonEntity;
import lesson.request.LessonCreation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lesson.repository.LessonRepository;

import java.util.List;


@Service
@Transactional
public class LessonsService {

    @Autowired
    private LessonRepository lessonRepository;

    public LessonEntity add(final LessonCreation lesson) {
        val lessonEntity = new LessonEntity();
        lessonEntity.setName(lesson.getName());
        lessonEntity.setDescription(lesson.getDescription());
        return lessonRepository.save(lessonEntity);
    }

    public List<LessonEntity> getList() {
        return (List<LessonEntity>) lessonRepository.findAll();
    }

    public void delete(final LessonEntity lessonEntity) {
        lessonRepository.delete(lessonEntity);
    }

    public LessonEntity get(final Long id) {
        return lessonRepository.findOne(id);
    }

    public LessonEntity update(final LessonEntity lessonEntity){
        return lessonRepository.save(lessonEntity);
    }
}