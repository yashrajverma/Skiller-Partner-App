package com.yashraj.skillerpartnerapp.Model;

public class OngoingTask {
    String location;
    String startingDate;
    String endingDate;
    String vendorId;
    String workId;
    String completed;

    public OngoingTask() {
    }

    public OngoingTask(String location, String startingDate, String endingDate, String vendorId, String workId, String completed) {
        this.location = location;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.vendorId = vendorId;
        this.workId = workId;
        this.completed = completed;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }
}
