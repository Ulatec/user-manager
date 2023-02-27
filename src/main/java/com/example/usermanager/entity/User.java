package com.example.usermanager.entity;



import com.example.usermanager.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.*;


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

    private SubscriptionStatus subscriptionStatus;

    private Date subscriptionExpiration;
    @OneToMany
    private List<Setting> settings = new ArrayList<>();
    @OneToMany
    private List<Purchase> purchases = new ArrayList<>();

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
    public void pushPurchase(Purchase purchase){
        if(purchase != null){
            if(purchases == null){
                purchases = new ArrayList<>();
            }
            purchases.add(purchase);
            purchase.setUser(this);
        }
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SubscriptionStatus getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public Date getSubscriptionExpiration() {
        return subscriptionExpiration;
    }

    public void setSubscriptionExpiration(Date subscriptionExpiration) {
        this.subscriptionExpiration = subscriptionExpiration;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
