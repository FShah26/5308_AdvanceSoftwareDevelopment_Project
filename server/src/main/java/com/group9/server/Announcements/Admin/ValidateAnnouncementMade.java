package com.group9.server.Announcements.Admin;

import org.springframework.stereotype.Component;

@Component
public class ValidateAnnouncementMade implements IValidateAnnouncementMade {
    @Override
    public Boolean validateAnnouncement(String message) {
        if (message.length() < 2000) {
            return true;
        }
        return false;
    }
}
