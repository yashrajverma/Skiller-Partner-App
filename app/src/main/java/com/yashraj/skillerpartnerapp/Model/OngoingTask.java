package com.yashraj.skillerpartnerapp.Model;

public class OngoingTask {
    String location;
    String starting_date;

    public OngoingTask() {
    }

    public OngoingTask(String location, String starting_date) {
        this.location = location;
        this.starting_date = starting_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(String starting_date) {
        this.starting_date = starting_date;
    }
}
