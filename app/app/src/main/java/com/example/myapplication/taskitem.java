package com.example.myapplication;

public class taskitem {

    private String task;
    private String title;
    private String date;
    private String time;
    private String description;

    public taskitem(String task, String title, String date, String time, String description) {
        this.task = task;
        this.title = title;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
