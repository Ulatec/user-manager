package com.example.usermanager.Service;



import com.example.usermanager.dto.PushResponse;
import com.example.usermanager.dto.SettingResponse;
import com.example.usermanager.dto.UpdateRequest;

import java.security.Principal;

public interface SettingService {

    SettingResponse getSetting(UpdateRequest updateRequest);
    PushResponse updateSetting(UpdateRequest updateRequest);


}
