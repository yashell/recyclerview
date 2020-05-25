package com.example.recyclerview;

public class DataEntity {
    private String title;
    private int imageId;
    private int status;
    private String time;
    public DataEntity(String title, int imageId, int status,String time) {
        this.title = title;
        this.imageId = imageId;
        this.status = status;
        this.time = time;
    }
    public String getTitle() {
        return title;
    }
    public int getImageId() {
        return imageId;
    }
    public int getStatus() { return status; }
    public String getTime() {
        return time;
    }
}

