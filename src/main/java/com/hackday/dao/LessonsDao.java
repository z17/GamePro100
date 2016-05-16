package com.hackday.dao;


import com.hackday.tables.LessonTable;
import com.hackday.entity.LessonEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LessonsDao extends AbstractDao {

    @SuppressWarnings("unchecked")
    public List<LessonEntity> getList() {
        final Criteria criteria = getSession().createCriteria(LessonEntity.class);
        return (List<LessonEntity>) criteria.list();
    }

    public void delete(final Long id) {
        final Query query = getSession().createSQLQuery("delete from lesson where id = :id");
        query.setLong(LessonTable.ID, id);
        query.executeUpdate();
    }


    public LessonEntity get(final Long id){
        final Criteria criteria = getSession().createCriteria(LessonEntity.class);
        criteria.add(Restrictions.eq(LessonTable.ID,id));
        return (LessonEntity) criteria.uniqueResult();
    }

    public void update(final LessonEntity LessonEntity){
        getSession().update(LessonEntity);
    }

}