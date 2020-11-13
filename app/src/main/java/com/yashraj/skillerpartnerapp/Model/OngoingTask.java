package com.yashraj.skillerpartnerapp.Model;

public class OngoingTask {
    String location;
    String startingDate;
    String endingDate;
    String vendorId;
    String workId;
    String completed;
    String description;
    String vendorName;
    int charges;

    public OngoingTask() {
    }

    public OngoingTask(String location, String startingDate, String endingDate, String vendorId, String workId, String completed, String description, String vendorName, int charges) {
        this.location = location;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.vendorId = vendorId;
        this.workId = workId;
        this.completed = completed;
        this.description = description;
        this.vendorName = vendorName;
        this.charges = charges;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }
}
