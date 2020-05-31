package com.example.covid_19demo;

public class User {
    private String username, mobnum, email;

    public User(){
    }

    public User(String username, String mobnum, String email) {
        this.username = username;
        this.mobnum = mobnum;
        this.email = email;

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobnum() {
        return mobnum;
    }

    public void setMobnum(String mobnum) {
        this.mobnum = mobnum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }






}
