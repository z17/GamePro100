package com.hackday.entity;


import com.hackday.tables.UsersTable;
import lombok.Data;

import javax.persistence.*;

@Data
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
}