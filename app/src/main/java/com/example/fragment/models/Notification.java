package com.example.fragment.models;

public class Notification {
    private String message;
    private long timestamp;

    public Notification(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

