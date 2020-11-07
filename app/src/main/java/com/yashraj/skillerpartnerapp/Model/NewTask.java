package com.yashraj.skillerpartnerapp.Model;

public class NewTask {
    String accepted;
    String address;
    long charges;
    String description;
    String duration;
    String location;
    String phoneNo;
    String user_name;
    String workId;


    public NewTask() {

    }

    public NewTask(String accepted, String address, long charges, String description, String duration, String location, String phoneNo, String user_name, String workId) {
        this.accepted = accepted;
        this.address = address;
        this.charges = charges;
        this.description = description;
        this.duration = duration;
        this.location = location;
        this.phoneNo = phoneNo;
        this.user_name = user_name;
        this.workId = workId;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted() {
        this.accepted = accepted;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCharges() {
        return charges;
    }

    public void setCharges(long charges) {
        this.charges = charges;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }
}
