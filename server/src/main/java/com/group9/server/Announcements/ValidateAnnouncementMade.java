package com.group9.server.Announcements;

import com.group9.server.Announcements.IValidateAnnouncementMade;
import org.springframework.stereotype.Component;

@Component
public class ValidateAnnouncementMade implements IValidateAnnouncementMade {
    @Override
    public Boolean validate_announcement(String message) {
        if(message.length()<2000)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
