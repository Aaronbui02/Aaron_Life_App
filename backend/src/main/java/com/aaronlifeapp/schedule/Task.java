package com.aaronlifeapp.schedule;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String date;
    private boolean completed;

    @ManyToOne
    private TaskList taskList;

    public void setId(String id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public String getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getDate() {
        return this.date;
    }
    public boolean isCompleted() {
        return this.completed;
    }
    public TaskList getTaskList() {
        return this.taskList;
    }
}