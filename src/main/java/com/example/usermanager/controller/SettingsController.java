package com.example.usermanager.controller;



import com.example.usermanager.Service.SettingService;
import com.example.usermanager.dto.PushResponse;
import com.example.usermanager.dto.SettingResponse;
import com.example.usermanager.dto.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/")
public class SettingsController {

    private SettingService settingService;



    @Autowired
    public SettingsController(SettingService settingService){
        this.settingService = settingService;
    }

    @PostMapping("/updateSetting")
    public PushResponse updateSetting(Principal principal, @RequestBody UpdateRequest updateRequest){
        if(principal.getName().equals(updateRequest.getEmail())) {
            return settingService.updateSetting(updateRequest);
        }else{
            return null;
        }
    }

    @GetMapping("/getSetting/{email}/{key}")
    public SettingResponse getSetting(Principal principal, @PathVariable String email, @PathVariable String key){
        if(principal.getName().equals(email)) {
            System.out.println(principal);
            return settingService.getSetting(email, key);
        }else{
            return null;
        }
    }


}
