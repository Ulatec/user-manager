package com.example.usermanager.controller;

import com.example.usermanager.Service.PurchaseService;
import com.example.usermanager.dto.PaymentInfo;
import com.example.usermanager.dto.PurchaseResponse;
import com.example.usermanager.dto.StripeResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.logging.Logger;

@RestController
public class PurchaseController {

    private Logger logger = Logger.getLogger(getClass().getName());
    @Autowired
    private PurchaseService purchaseService;


    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {
        logger.info("paymentInfo.amount: " + paymentInfo.getAmount());
        PaymentIntent paymentIntent = purchaseService.createPaymentIntent(paymentInfo);

        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> purchase(@RequestBody StripeResponse stripeResponse) throws StripeException {

        logger.info("stripeResponse: " + stripeResponse);
         PurchaseResponse purchaseResponse = purchaseService.processPurchase(stripeResponse);


        return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);
    }
}
