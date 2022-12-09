package com.example.studentlist.model;

import java.io.Serializable;

public class Student implements Serializable {
    public String name;
    public String id;
    public String phone;
    public String address;
    public String avatarUrl;
    public Boolean cb;

    public Student(String name, String id, String avatarUrl,
                   Boolean cb, String phone, String address) {
        this.name = name;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.cb = cb;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Boolean getCb() {
        return cb;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setCb(Boolean cb) {
        this.cb = cb;
    }
}
