package com.hackday.entity;

import com.hackday.tables.TaskTable;

import javax.persistence.*;

@Entity
@Table(name = TaskTable.TABLE_NAME)
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = TaskTable.LESSON_Id, nullable = false)
    private LessonEntity lessonEntity;

    @Column(name = TaskTable.NAME, nullable = false)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setLessonEntity(LessonEntity lessonEntity) {
        this.lessonEntity = lessonEntity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LessonEntity getLessonEntity() {
        return lessonEntity;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", lessonEntity=" + lessonEntity +
                ", name='" + name + '\'' +
                '}';
    }
}
