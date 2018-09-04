package com.maple.goindia;

public class Study {
    String goal, code, path;

    Study(String goal, String code, String path) {
        this.goal = goal;
        this.code = code;
        this.path = path;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
