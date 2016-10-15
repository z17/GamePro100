package lesson.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lesson.table.TaskTable;
import service_client.data.Task;

import javax.persistence.*;

@Data
@Entity
@Table(name = TaskTable.TABLE_NAME)
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = TaskTable.LESSON_Id, nullable = false)
    private LessonEntity lesson;

    @JsonIgnore
    @Column(name = TaskTable.LESSON_Id, updatable = false, insertable = false)
    private Long lessonId;

    @Column(name = TaskTable.NAME, nullable = false)
    private String name;

    @Column(name = TaskTable.DESCRIPTION, nullable = false)
    private String description;

    @Column(name = TaskTable.MAP_PATH, nullable = false)
    private String mapPath;

    public Task toDto() {
        return new Task(id, lessonId, name, description, mapPath);
    }
}