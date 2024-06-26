package com.example.utokyoprompts.entity;

import jakarta.persistence.*;

@Entity
public class UserLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Lessons class_id;

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public Lessons getClass_id() {
        return class_id;
    }

    public void setClass_id(Lessons class_id) {
        this.class_id = class_id;
    }
}