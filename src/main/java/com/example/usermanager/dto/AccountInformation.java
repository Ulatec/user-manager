package com.example.usermanager.dto;

import java.util.Date;

public class AccountInformation {

    public String email;
    public boolean subscriptionActive;
    public Date subscriptionExpiration;

    public AccountInformation(String email, boolean subscriptionActive, Date subscriptionExpiration) {
        this.email = email;
        this.subscriptionActive = subscriptionActive;
        this.subscriptionExpiration = subscriptionExpiration;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSubscriptionActive() {
        return subscriptionActive;
    }

    public void setSubscriptionActive(boolean subscriptionActive) {
        this.subscriptionActive = subscriptionActive;
    }

    public Date getSubscriptionExpiration() {
        return subscriptionExpiration;
    }

    public void setSubscriptionExpiration(Date subscriptionExpiration) {
        this.subscriptionExpiration = subscriptionExpiration;
    }
}
