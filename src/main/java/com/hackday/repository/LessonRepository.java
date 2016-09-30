package com.hackday.repository;

import com.hackday.entity.LessonEntity;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<LessonEntity, Long> {
}
