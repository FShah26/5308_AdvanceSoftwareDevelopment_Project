package com.group9.server.Announcements.Admin;

import org.springframework.stereotype.Component;

@Component
public class ValidateAnnouncementMade implements IValidateAnnouncementMade {
    @Override
    public Boolean validate_announcement(String message) {
        return message.length() < 2000;
    }
}
