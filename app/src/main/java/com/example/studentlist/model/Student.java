package com.example.studentlist.model;

import java.io.Serializable;

public class Student implements Serializable {
    public String name;
    public String id;
    public String phone;
    public String address;
    public String imgUrl;
    public Boolean cb;

    public Student(String name, String id, String avatarUrl,
                   Boolean cb, String phone, String address) {
        this.name = name;
        this.id = id;
        this.imgUrl = avatarUrl;
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

    public String getImgUrl() {
        return imgUrl;
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

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setCb(Boolean cb) {
        this.cb = cb;
    }
}
