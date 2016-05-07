package com.hackday.services;

import java.util.List;

import com.hackday.dao.LessonDao;
import com.hackday.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("lessonService")
@Transactional
public class LessonService {

    @Autowired
    private LessonDao dao;

    public void saveLesson(Lesson lesson) {
        dao.saveLesson(lesson);
    }

    public List<Lesson> findAllLesson() {
        return dao.findAllLessons();
    }

    public void deleteLessonByID(Long id) {
        dao.deleteLessonByID(id);
    }

    public Lesson findByID(Long id) {
        return dao.findByID(id);
    }

    public void updateLesson(Lesson lesson){
        dao.updateLesson(lesson);
    }
}