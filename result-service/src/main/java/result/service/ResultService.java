package result.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import result.entity.AnswerEntity;
import result.repository.AnswerRepository;
import service_client.client.ExecutorClient;
import service_client.client.TaskClient;
import service_client.client.UserClient;
import service_client.data.TaskResult;
import service_client.data.request.SubmitRequest;
import service_client.security.TokenUser;

import java.util.List;


@Service
@Transactional
public class ResultService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private TaskClient taskClient;

    @Autowired
    private ExecutorClient executorClient;

    public TaskResult submit(final SubmitRequest submit, final String token) {

        val task = taskClient.get(submit.getTaskId());
        if (task == null)
            throw new RuntimeException("Invalid task id");

        val user = userClient.get(getCurrentUser(), token);
        // todo: check access user to current lesson and task

        val result = executorClient.submit(submit, token);

        val answerEntity = new AnswerEntity();
        answerEntity.setAnswer(submit.getCode());
        answerEntity.setTaskId(task.getId());
        answerEntity.setUserId(user.getId());
        answerEntity.setCorrect(result.getStatus() == TaskResult.Status.SUCCESS);
        answerRepository.save(answerEntity);

        return result;
    }

    public List<AnswerEntity> getByTask(final Long taskID) {
        return answerRepository.findByUserIdAndTaskId(getCurrentUser(), taskID);
    }

    private long getCurrentUser() {
        return ((TokenUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
