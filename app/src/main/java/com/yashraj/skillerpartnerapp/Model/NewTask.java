package com.yashraj.skillerpartnerapp.Model;

public class NewTask {
    String description;
    String location;
    String phoneNo;
    String charges;
    String duration;
    String workId;
    String vendorId;
    String accepted;

    public NewTask() {
    }

    public NewTask(String description, String location, String phoneNo, String charges, String duration, String workId, String vendorId, String accepted) {
        this.description = description;
        this.location = location;
        this.phoneNo = phoneNo;
        this.charges = charges;
        this.duration = duration;
        this.workId = workId;
        this.vendorId = vendorId;
        this.accepted = accepted;
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

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }
}
