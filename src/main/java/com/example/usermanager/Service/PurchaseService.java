package com.example.usermanager.Service;

import com.example.usermanager.dto.AccountInformation;
import com.example.usermanager.dto.PaymentInfo;
import com.example.usermanager.dto.PurchaseResponse;
import com.example.usermanager.dto.StripeResponse;
import com.example.usermanager.entity.Purchase;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface PurchaseService {
    PurchaseResponse processPurchase(StripeResponse stripeResponse);
    AccountInformation checkIfUserSubscriptionActive(String email);
    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
