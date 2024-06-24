package com.example.utokyoprompts.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users owner_id;
    @ManyToOne
    @JoinColumn(name = "prompt_id")
    private Prompts prompt_id;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime deleted_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Users owner_id) {
        this.owner_id = owner_id;
    }

    public Prompts getPrompt_id() {
        return prompt_id;
    }

    public void setPrompt_id(Prompts prompt_id) {
        this.prompt_id = prompt_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalDateTime deleted_at) {
        this.deleted_at = deleted_at;
    }
}