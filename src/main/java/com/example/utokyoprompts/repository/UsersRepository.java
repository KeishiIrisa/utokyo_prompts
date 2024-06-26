package com.example.utokyoprompts.repository;

import com.example.utokyoprompts.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer> {
}