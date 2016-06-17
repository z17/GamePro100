package com.hackday.dao;

import com.hackday.entity.AnswerEntity;
import com.hackday.entity.TaskEntity;
import com.hackday.entity.UserEntity;
import com.hackday.special.LoggingUtility;
import com.hackday.tables.AnswersTable;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswersDao extends AbstractDao<AnswerEntity> {

    @SuppressWarnings("unchecked")
    public List<AnswerEntity> getByUserAndTask(final UserEntity user, final TaskEntity task) {
        final Criteria criteria = getSession().createCriteria(AnswerEntity.class);
        criteria.add(Restrictions.eq(AnswersTable.TASK_ENTITY, task));
        criteria.add(Restrictions.eq(AnswersTable.USER_ENTITY, user));
        return (List<AnswerEntity>)criteria.list();
    }
}
