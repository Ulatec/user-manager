package com.example.usermanager.Service;

import com.example.usermanager.dao.SettingRepository;
import com.example.usermanager.dao.UserRepository;
import com.example.usermanager.dto.PushResponse;
import com.example.usermanager.dto.SettingResponse;
import com.example.usermanager.dto.UpdateRequest;
import com.example.usermanager.entity.Setting;
import com.example.usermanager.entity.User;
import com.stripe.Stripe;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.*;

@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingRepository settingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public SettingServiceImpl(){
    }

    public SettingResponse getSetting(String email, String key){
        Setting setting = settingRepository.findAllByUserEmailAndKey(email, key);
        return new SettingResponse(setting.getKey(), setting.getValue());
    }
    @Override
    @Transactional
    public PushResponse updateSetting(UpdateRequest updateRequest) {
        if(updateRequest.getEmail() != null) {
            //Get user
            User user = userRepository.findByEmail(updateRequest.getEmail());
            if (user == null) {
                user = new User(updateRequest.getEmail());
            }
            Setting existingSetting = null;
            for(Setting setting: user.getSettings()){
                if(setting.getKey().equals(updateRequest.getSetting().getKey())){
                    existingSetting = setting;
                }
            }
            if(existingSetting!=null) {
                existingSetting.setValue(updateRequest.getSetting().getValue());
                settingRepository.save(existingSetting);
            }else{
                user.pushSetting(updateRequest.getSetting());
                updateRequest.getSetting().setUser(user);
                settingRepository.save(updateRequest.getSetting());
            }
            userRepository.save(user);
            return new PushResponse(true);
        }else{
            return new PushResponse(false);
        }

    }



    //To be used during payment/subscription implementation
    private String generateOrderNumber() {
        return UUID.randomUUID().toString();
    }
}
