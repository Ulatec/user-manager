package com.example.usermanager.controller;

import com.example.usermanager.Service.PurchaseService;
import com.example.usermanager.dto.AccountInformation;
import com.example.usermanager.dto.PaymentInfo;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.logging.Logger;

@RestController
public class AccountInformationController {
    private Logger logger = Logger.getLogger(getClass().getName());
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/isSubscriptionActive/{email}")
    public AccountInformation createPaymentIntent(Principal principal, @PathVariable String email) throws StripeException {
        logger.info("checking if " + email + " has an active subscription.");
        if(principal.getName().equals(email)) {
            System.out.println(principal);
            return purchaseService.checkIfUserSubscriptionActive(email);
        }else{
            return null;
        }
    }
}
