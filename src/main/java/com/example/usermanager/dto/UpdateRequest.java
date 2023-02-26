package com.example.usermanager.dto;


import com.example.usermanager.entity.Setting;
import lombok.Data;

@Data
public class UpdateRequest {

    private String email;

    private Setting setting;


    @Override
    public String toString() {
        return "UpdateRequest{" +
                "email='" + email + '\'' +
                ", setting=" + setting +
                '}';
    }
}
