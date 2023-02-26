package com.example.usermanager.entity;


import jakarta.persistence.*;

@Entity
@Table(name="_setting")
public class Setting {

    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="_status")
    private String status;

    private String key;
    @Column(name="_value")
    private int value;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", key='" + key + '\'' +
                ", value=" + value +
                ", user=" + user +
                '}';
    }
}
