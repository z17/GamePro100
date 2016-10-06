package lesson.service;

import lesson.entity.LessonEntity;
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

    public boolean add(final LessonEntity lessonEntity) {
        lessonRepository.save(lessonEntity);
        return true;
    }

    public List<LessonEntity> getList() {
        return (List<LessonEntity>) lessonRepository.findAll();
    }

    public void delete(LessonEntity lessonEntity) {
        lessonRepository.delete(lessonEntity);
    }

    public LessonEntity get(Long id) {
        return lessonRepository.findOne(id);
    }

    public void update(LessonEntity lessonEntity){
        lessonRepository.save(lessonEntity);
    }
}