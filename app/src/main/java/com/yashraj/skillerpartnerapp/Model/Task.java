package com.yashraj.skillerpartnerapp.Model;

public class Task {
    private String user_name;
    private String user_city;
    private String user_address;
    private String user_mobile;
    private String user_desc;
    private String user_duration;
    private String user_charges;
    private String userid;

    public Task(){}

    public Task(String user_address,String user_city,String user_charges,String user_desc,String user_duration,String user_mobile,String user_name,String userid) {
        this.user_name = user_name;
        this.user_city = user_city;
        this.user_address = user_address;
        this.user_mobile = user_mobile;
        this.user_desc=user_desc;
        this.user_duration = user_duration;
        this.userid = userid;
        this.user_charges=user_charges;
    }

    public String getUser_charges() {
        return user_charges;
    }

    public void setUser_charges(String user_charges) {
        this.user_charges = user_charges;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_duration() {
        return user_duration;
    }

    public void setUser_duration(String user_duration) {
        this.user_duration = user_duration;
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
