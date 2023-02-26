package com.example.usermanager.controller;



import com.example.usermanager.Service.SettingService;
import com.example.usermanager.dto.PushResponse;
import com.example.usermanager.dto.SettingResponse;
import com.example.usermanager.dto.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class SettingsController {

    private SettingService settingService;



    @Autowired
    public SettingsController(SettingService settingService){
        this.settingService = settingService;
    }

    @PostMapping("/updateSetting")
    public PushResponse updateSetting(@RequestBody UpdateRequest updateRequest){
        return settingService.updateSetting(updateRequest);
    }
    @GetMapping("/getSetting/{email}/{key}")
    public SettingResponse getSetting(@RequestHeader("Authorization") String header, @PathVariable String email, @PathVariable String key){
        return settingService.getSetting(email, key);
    }


}
