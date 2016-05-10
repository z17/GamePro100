package com.hackday.entity;

import com.hackday.tables.TaskTable;
import lombok.Data;

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
    private LessonEntity lessonEntity;

    @Column(name = TaskTable.NAME, nullable = false)
    private String name;
}
