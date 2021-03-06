package com.yashraj.skillerpartnerapp.Model;

public class Vendor {
    String Name, Email, Password, Gender, State, City, Occupation, PhoneNo, ImageUrl;
    int Charges;

    public Vendor() {
    }


    public Vendor(String name, String email, String password, String gender, String state, String city, String occupation, String phoneNo, String imageUrl, int charges) {
        Name = name;
        Email = email;
        Password = password;
        Gender = gender;
        State = state;
        City = city;
        Occupation = occupation;
        PhoneNo = phoneNo;
        this.Charges = charges;
        this.ImageUrl = imageUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.ImageUrl = imageUrl;
    }

    public int getCharges() {
        return Charges;
    }

    public void setCharges(int charges) {
        this.Charges = charges;
    }
}
