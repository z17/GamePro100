package com.hackday.entity;

import javax.persistence.*;

@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Column(name = "name", nullable = false)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Lesson getLesson() {
        return lesson;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", lesson=" + lesson +
                ", name='" + name + '\'' +
                '}';
    }
}
