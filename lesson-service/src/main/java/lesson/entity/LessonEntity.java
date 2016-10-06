package lesson.entity;


import lombok.Data;
import lesson.table.LessonTable;

import javax.persistence.*;

@Data
@Entity
@Table(name = LessonTable.TABLE_NAME)
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = LessonTable.NAME, nullable = false)
    private String name;
}
