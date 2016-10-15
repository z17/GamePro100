package lesson.service;

import lesson.entity.LessonEntity;
import lesson.request.LessonCreation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lesson.repository.LessonRepository;
import service_client.data.Lesson;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class LessonsService {

    @Autowired
    private LessonRepository lessonRepository;

    public Lesson add(final LessonCreation lesson) {
        val lessonEntity = new LessonEntity();
        lessonEntity.setName(lesson.getName());
        lessonEntity.setDescription(lesson.getDescription());
        return lessonRepository.save(lessonEntity).toDto();
    }

    public List<Lesson> getList() {
        return ((List<LessonEntity>) lessonRepository.findAll())
                .stream()
                .map(LessonEntity::toDto)
                .collect(Collectors.toList());
    }

    public void delete(final LessonEntity lessonEntity) {
        lessonRepository.delete(lessonEntity);
    }

    public Lesson get(final Long id) {
        return lessonRepository.findOne(id).toDto();
    }

    public Lesson update(final LessonEntity lessonEntity){
        return lessonRepository.save(lessonEntity).toDto();
    }
}