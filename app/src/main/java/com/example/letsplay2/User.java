package com.example.letsplay2;

public class User {

    public String name;
    public String mail;
    public String address;
    public String sports;
    public String pass;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public User(String name, String mail, String address, String sports, String pass) {
        this.name = name;
        this.mail = mail;
        this.address = address;
        this.sports = sports;
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getAddress() {
        return address;
    }

    public String getSports() {
        return sports;
    }

}
