package user.entity;


import lombok.Data;
import service_client.data.User;
import service_client.data.UserRole;
import user.table.UserTable;

import javax.persistence.*;

@Data
@Entity
@Table(name = UserTable.TABLE_NAME)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = UserTable.LOGIN, nullable = false, unique = true, length = 45)
    private String login;

    @Column(name = UserTable.PASSWORD, nullable = false, length = 60)
    private String password;

    @Column(name = UserTable.GROUP, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole group;

    @Column(name = UserTable.EMAIL, nullable = false, length = 60)
    private String email;

    @Column(name = UserTable.NAME, nullable = false)
    private String name;

    public User toDto() {
        return new User(id, login, group, email, name);
    }
}