package com.example.SmartCM.DTOS;

import com.example.SmartCM.Model.userType;

public class updateUserDTO {

    private Integer userid;
    private userType role;

    public updateUserDTO() {
    }

    public updateUserDTO(Integer userid, userType role) {
        this.userid = userid;
        this.role = role;
    }

    public userType getRole() {
        return role;
    }

    public void setRole(userType role) {
        this.role = role;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
