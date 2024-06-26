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
    @JoinColumn(name = "lesson_id")
    private Lessons lesson_id;

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public Lessons getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Lessons class_id) {
        this.lesson_id = lesson_id;
    }
}