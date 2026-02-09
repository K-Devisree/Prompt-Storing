package com.build.prompt_storing.model;

import java.sql.Timestamp;

public class Prompt {
    private int id;
    private String title;
    private String content;
    private String category;
    private Timestamp createdAt;

    public Prompt() {}

    public Prompt(int id, String title, String content, String category, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}