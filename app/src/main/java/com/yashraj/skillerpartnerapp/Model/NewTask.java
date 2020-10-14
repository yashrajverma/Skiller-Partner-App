package com.yashraj.skillerpartnerapp.Model;

public class NewTask {
    String description;
    String location;
    String phoneNo;
    String charges;
    String duration;

    public NewTask() {
    }

    public NewTask(String description, String location, String phoneNo, String charges, String duration) {
        this.description = description;
        this.location = location;
        this.phoneNo = phoneNo;
        this.charges = charges;
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
