package com.example.gbpiet.authentication;

public class RegisterData {
    private String image, name,lastName,gender, branch ,email, key,date,time;

    public RegisterData() {
    }

    public RegisterData(String image, String name, String lastName ,String gender,String branch, String email, String key,String date, String time) {
        this.image = image;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.branch = branch;
        this.email = email;
        this.key = key;
        this.date = date;
        this.time = time;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public RegisterData(String image) {
        this.image = image;

    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
