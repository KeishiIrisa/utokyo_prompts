package com.example.utokyoprompts.repository;

import com.example.utokyoprompts.entity.Prompts;
import org.springframework.data.repository.CrudRepository;

public interface PromptsRepository extends CrudRepository<Prompts, Integer> {
}
