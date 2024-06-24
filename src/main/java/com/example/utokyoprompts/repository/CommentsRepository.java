package com.example.utokyoprompts.repository;

import com.example.utokyoprompts.entity.Comments;
import org.springframework.data.repository.CrudRepository;

public interface CommentsRepository extends CrudRepository<Comments, Integer> {
}
