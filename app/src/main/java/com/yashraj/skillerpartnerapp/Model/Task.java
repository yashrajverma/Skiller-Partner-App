package com.yashraj.skillerpartnerapp.Model;

public class Task {
    String user_name;
    String user_city;
    String user_address;
    String user_mobile;
    String user_desc;
    String duration;


    public Task(){}

    public Task(String user_name, String user_city, String user_address, String user_mobile,String user_desc,String duration) {
        this.user_name = user_name;
        this.user_city = user_city;
        this.user_address = user_address;
        this.user_mobile = user_mobile;
        this.user_desc=user_desc;
        this.duration=duration;
    }
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUser_desc() {
        return user_desc;
    }

    public void setUser_desc(String user_desc) {
        this.user_desc = user_desc;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }
}
