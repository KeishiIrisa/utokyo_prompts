package com.example.utokyoprompts.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDateTime created_at;
    private LocalDateTime deleted_at;

    @OneToMany
    List<UserLesson> userLessons;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        if (this.created_at == null) {
            this.created_at = created_at;
        }
    }

    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now();
    }
}
