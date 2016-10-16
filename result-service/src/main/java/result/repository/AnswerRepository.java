package result.repository;

import org.springframework.data.repository.CrudRepository;
import result.entity.AnswerEntity;

import java.util.List;

public interface AnswerRepository extends CrudRepository<AnswerEntity, Long> {
    List<AnswerEntity> findByUserIdAndTaskId(final Long currentUser, final Long taskEntity);
}
