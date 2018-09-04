package com.maple.goindia;

public class Graph_Path {
    String classes, duration;

    Graph_Path(String classes, String duration) {
        this.classes = classes;
        this.duration = duration;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
