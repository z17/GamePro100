package com.hackday.services;

import com.hackday.entity.LessonEntity;
import com.hackday.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class LessonsService {

    @Autowired
    private LessonRepository lessonRepository;

    public boolean create(final LessonEntity lessonEntity) {
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