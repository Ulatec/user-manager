package com.example.usermanager.dto;

import lombok.Data;

@Data
public class PaymentInfo {

    private int amount;
    private String currency;

    private String email;


}
