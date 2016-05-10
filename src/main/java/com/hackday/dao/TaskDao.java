package com.hackday.dao;

import com.hackday.tables.TaskTable;
import com.hackday.entity.TaskEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDao extends AbstractDao {

    public TaskEntity get(final Long id){
        final Criteria criteria = getSession().createCriteria(TaskEntity.class);
        criteria.add(Restrictions.eq(TaskTable.ID,id));

        return (TaskEntity) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<TaskEntity> getListByLesson(Long lessonID) {
        final Criteria criteria = getSession().createCriteria(TaskEntity.class);
        criteria.add(Restrictions.eq(TaskTable.LESSON_Id, lessonID));

        return (List<TaskEntity>) criteria.list();
    }
}
