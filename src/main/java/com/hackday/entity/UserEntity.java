package com.hackday.entity;


import com.hackday.tables.UsersTable;

import javax.persistence.*;

@Entity
@Table(name = UsersTable.TABLE_NAME)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = UsersTable.LOGIN, nullable = false, unique = true, length = 45)
    private String login;

    @Column(name = UsersTable.PASSWORD, nullable = false, length = 60)
    private String password;

    @Column(name = UsersTable.GROUP, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole group;

    @Column(name = UsersTable.EMAIL, nullable = false)
    private String email;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getGroup() {
        return group;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGroup(UserRole group) {
        this.group = group;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



}