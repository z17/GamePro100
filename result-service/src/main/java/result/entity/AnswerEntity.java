package result.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "answers")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "correct", nullable = false)
    private Boolean correct;
}
