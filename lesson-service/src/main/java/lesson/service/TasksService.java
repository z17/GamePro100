package lesson.service;

import lesson.entity.LessonEntity;
import lesson.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lesson.repository.TaskRepository;
import lesson.request.TaskCreation;

import java.util.List;

@Service
@Transactional
public class TasksService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskEntity get(final Long id) {
        return taskRepository.findOne(id);
    }

    public List<TaskEntity> getListByLesson(final Long lessonID) {
        return taskRepository.getListByLessonId(lessonID);
    }

    public boolean add(final TaskCreation taskArgs) {
        final TaskEntity task = new TaskEntity();
        task.setName(taskArgs.name);
        task.setDescription(taskArgs.description);
        task.setMapPath(taskArgs.mapPath);     // todo: save map as a file? or something else
        final LessonEntity lessonTask = new LessonEntity();
        lessonTask.setId(taskArgs.lessonID);
        task.setLesson(lessonTask);
        taskRepository.save(task);
        return true;
    }
}
