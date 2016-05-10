package com.hackday.dao;


import com.hackday.entity.LessonEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("lessonDao")
public class LessonDao extends AbstractDao {

    public void create(LessonEntity lessonEntity) {
        persist(lessonEntity);
    }

    @SuppressWarnings("unchecked")
    public List<LessonEntity> getList() {
        Criteria criteria = getSession().createCriteria(LessonEntity.class);
        return (List<LessonEntity>) criteria.list();
    }

    public void delete(Long id) {
        Query query = getSession().createSQLQuery("delete from lesson where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }


    public LessonEntity get(Long id){
        Criteria criteria = getSession().createCriteria(LessonEntity.class);
        criteria.add(Restrictions.eq("id",id));
        return (LessonEntity) criteria.uniqueResult();
    }

    public void update(LessonEntity LessonEntity){
        getSession().update(LessonEntity);
    }

}