package lesson.service;

import lesson.entity.LessonEntity;
import lesson.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lesson.repository.TaskRepository;
import service_client.data.Task;
import service_client.data.request.TaskCreation;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TasksService {

    @Autowired
    private TaskRepository taskRepository;

    public Task get(final Long id) {
        return taskRepository.findOne(id).toDto();
    }

    public List<Task> getListByLesson(final Long lessonID) {
        return taskRepository.findByLessonId(lessonID)
                .stream()
                .map(TaskEntity::toDto)
                .collect(Collectors.toList());
    }

    public List<Task>getList() {
        return ((List<TaskEntity>)taskRepository.findAll())
                .stream()
                .map(TaskEntity::toDto)
                .collect(Collectors.toList());
    }

    public Task add(final TaskCreation taskArgs) {
        final TaskEntity task = new TaskEntity();
        task.setName(taskArgs.getName());
        task.setDescription(taskArgs.getDescription());
        task.setMapPath(taskArgs.getMapPath());     // todo: save map as a file? or something else
        final LessonEntity lessonTask = new LessonEntity();
        lessonTask.setId(taskArgs.getLessonID());
        task.setLesson(lessonTask);
        return taskRepository.save(task).toDto();
    }

}
