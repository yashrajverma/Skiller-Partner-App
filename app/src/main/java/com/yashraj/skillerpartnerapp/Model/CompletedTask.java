package com.yashraj.skillerpartnerapp.Model;

public class CompletedTask {
    String location, startingDate, endingDate, payment, vendorId, workId;

    public CompletedTask() {
    }

    public CompletedTask(String location, String startingDate, String endingDate, String payment, String vendorId, String workId) {
        this.location = location;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.payment = payment;
        this.vendorId = vendorId;
        this.workId = workId;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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
}
