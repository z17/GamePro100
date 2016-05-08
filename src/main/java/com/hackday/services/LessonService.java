package com.hackday.services;

import java.util.List;

import com.hackday.dao.LessonDao;
import com.hackday.entity.LessonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("lessonService")
@Transactional
public class LessonService {

    @Autowired
    private LessonDao dao;

    public void saveLesson(LessonEntity lessonEntity) {
        dao.saveLesson(lessonEntity);
    }

    public List<LessonEntity> findAllLesson() {
        return dao.findAllLessons();
    }

    public void deleteLessonByID(Long id) {
        dao.deleteLessonByID(id);
    }

    public LessonEntity findByID(Long id) {
        return dao.findByID(id);
    }

    public void updateLesson(LessonEntity lessonEntity){
        dao.updateLesson(lessonEntity);
    }
}