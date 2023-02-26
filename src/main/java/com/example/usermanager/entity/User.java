package com.example.usermanager.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="_user")
@Getter
@Setter
public class User {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @OneToMany
    private List<Setting> settings = new ArrayList<>();

    public User(String email) {
        this.email = email;
    }

    public User() {

    }

    public void pushSetting(Setting setting){
        if(setting != null){
            if(settings == null){
                settings = new ArrayList<>();
            }
            settings.add(setting);
            setting.setUser(this);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
