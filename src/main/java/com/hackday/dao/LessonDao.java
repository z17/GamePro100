package com.hackday.dao;


import com.hackday.entity.Lesson;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("lessonDao")
public class LessonDao extends AbstractDao {

    public void saveLesson(Lesson lesson) {
        persist(lesson);
    }

    @SuppressWarnings("unchecked")
    public List<Lesson> findAllLessons() {
        Criteria criteria = getSession().createCriteria(Lesson.class);
        return (List<Lesson>) criteria.list();
    }

    public void deleteLessonByID(Long id) {
        Query query = getSession().createSQLQuery("delete from lesson where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }


    public Lesson findByID(Long id){
        Criteria criteria = getSession().createCriteria(Lesson.class);
        criteria.add(Restrictions.eq("id",id));
        return (Lesson) criteria.uniqueResult();
    }

    public void updateLesson(Lesson Lesson){
        getSession().update(Lesson);
    }

}