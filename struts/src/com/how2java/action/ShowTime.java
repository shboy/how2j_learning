package com.how2java.action;

public class ShowTime {
    private String currentTime;

    public String getCurrentTime() {
        this.setCurrentTime(String.valueOf(System.currentTimeMillis()));
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
