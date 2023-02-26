package com.example.usermanager.Service;



import com.example.usermanager.dto.PushResponse;
import com.example.usermanager.dto.SettingResponse;
import com.example.usermanager.dto.UpdateRequest;

public interface SettingService {

    SettingResponse getSetting(String email, String key);
    PushResponse updateSetting(UpdateRequest updateRequest);


}
