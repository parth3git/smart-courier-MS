package com.example.SmartCM.Model;

import jakarta.persistence.*;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    private String uname;

    @Enumerated(EnumType.STRING)
    private userType role;

    public Users() {
    }

    public Users(int uid, String uname, userType role) {
        this.uid = uid;
        this.uname = uname;
        this.role = role;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public userType getRole() {
        return role;
    }

    public void setRole(userType role) {
        this.role = role;
    }
}
