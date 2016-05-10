package com.hackday.dao;

import com.hackday.tables.UsersTable;
import com.hackday.entity.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao extends AbstractDao {

    @SuppressWarnings("unchecked")
    public UserEntity findByUserName(String login) {
        Criteria criteria = getSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq(UsersTable.LOGIN, login));

        return (UserEntity) criteria.uniqueResult();
    }
}
