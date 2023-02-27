package com.example.usermanager.Service;


import com.example.usermanager.dao.PurchaseRepository;
import com.example.usermanager.dao.UserRepository;
import com.example.usermanager.dto.AccountInformation;
import com.example.usermanager.dto.PaymentInfo;
import com.example.usermanager.dto.PurchaseResponse;
import com.example.usermanager.dto.StripeResponse;
import com.example.usermanager.entity.Purchase;
import com.example.usermanager.entity.User;
import com.example.usermanager.enums.SubscriptionStatus;
import com.netflix.discovery.converters.Auto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class PurchaseServiceImpl implements  PurchaseService{




    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(@Value("${stripe.key.secret}") String secretKey){
        Stripe.apiKey = secretKey;
    }
    @Override
    @Transactional
    public PurchaseResponse processPurchase(StripeResponse stripeResponse) {
        User user = userRepository.findByEmail(stripeResponse.getEmail());
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(new Date());
        purchase.setExpirationDate(Date.from(LocalDate.now().plusMonths(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        purchase.setUser(user);
        purchase.setAmount(stripeResponse.getAmount());
        purchaseRepository.save(purchase);
        user.pushPurchase(purchase);
        user.setSubscriptionStatus(SubscriptionStatus.ACTIVE);
        user.setSubscriptionExpiration(purchase.getExpirationDate());
        userRepository.save(user);
        return new PurchaseResponse(purchase.getExpirationDate(), true);
    }


    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
        System.out.println(paymentInfo);
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");


        Map<String,Object> params = new HashMap<>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        params.put("description", "e-commerce purchase");
        params.put("receipt_email", paymentInfo.getEmail());
        return PaymentIntent.create(params);
    }
    @Override
    public AccountInformation checkIfUserSubscriptionActive(String email) {
        User user = userRepository.findByEmail(email);
        Date date = new Date();
        boolean active;
        if(user.getSubscriptionExpiration() == null){
            active = false;
        }else{
            active = user.getSubscriptionExpiration().after(date);
        }
        return new AccountInformation(user.getEmail(),active ,user.getSubscriptionExpiration());
    }
}
