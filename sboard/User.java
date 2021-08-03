package com.info.sboard;

public class User {
    private int user_id;
    private String user_name;
    private String user_surname;
    private String user_phone;

    public User() {
    }

    public User(int user_id, String user_name, String user_surname, String user_phone) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_phone = user_phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }
}
