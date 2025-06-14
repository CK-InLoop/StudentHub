package com.edutrack.model;

import java.sql.Timestamp;

public class Notice {
    private int id;
    private String title;
    private String message;
    private Timestamp postedOn;

    public Notice() {}

    public Notice(int id, String title, String message, Timestamp postedOn) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.postedOn = postedOn;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Timestamp getPostedOn() { return postedOn; }
    public void setPostedOn(Timestamp postedOn) { this.postedOn = postedOn; }
}
