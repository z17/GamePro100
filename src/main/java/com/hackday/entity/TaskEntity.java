package com.hackday.entity;

import javax.persistence.*;

@Entity
@Table(name="task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private LessonEntity lessonEntity;

    @Column(name = "name", nullable = false)
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
