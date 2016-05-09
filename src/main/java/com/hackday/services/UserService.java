package com.hackday.services;

import com.hackday.dao.UserDao;
import com.hackday.entity.UserEntity;
import com.hackday.entity.UserRole;
import com.hackday.requests.UserArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public boolean create(UserArguments userArguments) {
        UserEntity user = new UserEntity();
        user.setLogin(userArguments.login);
        user.setPassword(bcryptEncoder.encode(userArguments.password));
        user.setGroup(UserRole.USER);
        dao.persist(user);

        return true;
    }
}