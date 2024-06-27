package com.example.utokyoprompts.repository;

import com.example.utokyoprompts.entity.Lessons;
import com.example.utokyoprompts.entity.UserLesson;
import com.example.utokyoprompts.entity.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserLessonRepository extends CrudRepository<UserLesson, Integer> {
    Optional<UserLesson> findByUserAndLesson(Users user, Lessons lesson);
}
