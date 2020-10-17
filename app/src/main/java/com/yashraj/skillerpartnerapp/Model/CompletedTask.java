package com.yashraj.skillerpartnerapp.Model;

public class CompletedTask {
    String location, startingDate, endingDate, payment;

    public CompletedTask() {
    }

    public CompletedTask(String location, String startingDate, String endingDate, String payment) {
        this.location = location;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.payment = payment;
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
}
