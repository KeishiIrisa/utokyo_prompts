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
    private Users owner;
    @ManyToOne
    @JoinColumn(name = "prompt_id")
    private Prompts prompt;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime deleted_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner_id) {
        this.owner = owner_id;
    }

    public Prompts getPrompt() {
        return prompt;
    }

    public void setPrompt(Prompts prompt_id) {
        this.prompt = prompt_id;
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