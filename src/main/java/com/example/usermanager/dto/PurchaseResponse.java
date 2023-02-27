package com.example.usermanager.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PurchaseResponse {

    private final Date expiration_date;
    private boolean status;

    public PurchaseResponse(Date expiration_date, boolean status) {
        this.expiration_date = expiration_date;
        this.status = status;
    }
}
