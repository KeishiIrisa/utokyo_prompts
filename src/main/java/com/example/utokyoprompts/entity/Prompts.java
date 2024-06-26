package com.example.utokyoprompts.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Prompts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String prompts;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users owner_id;
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Lessons class_id;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrompts() {
        return prompts;
    }

    public void setPrompts(String prompts) {
        this.prompts = prompts;
    }

    public Users getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Users owner_id) {
        this.owner_id = owner_id;
    }

    public Lessons getClass_id() {
        return class_id;
    }

    public void setClass_id(Lessons class_id) {
        this.class_id = class_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getModified_at() {
        return modified_at;
    }

    public void setModified_at(LocalDateTime modified_at) {
        this.modified_at = modified_at;
    }
}