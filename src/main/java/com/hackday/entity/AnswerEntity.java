package com.hackday.entity;

import com.hackday.tables.AnswersTable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = AnswersTable.TABLE_NAME)
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = AnswersTable.USER_ID, nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = AnswersTable.TASK_ID, nullable = false)
    private TaskEntity taskEntity;

    @Column(name = AnswersTable.ANSWER, nullable = false)
    private String answer;

    @Column(name = AnswersTable.CORRECT, nullable = false)
    private Boolean correct;
}
