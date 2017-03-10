package user.repository;

import org.springframework.data.repository.CrudRepository;
import user.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
}
