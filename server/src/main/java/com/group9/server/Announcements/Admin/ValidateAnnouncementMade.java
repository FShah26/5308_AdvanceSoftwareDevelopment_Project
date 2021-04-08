package com.group9.server.Announcements.Admin;

import org.springframework.stereotype.Component;

@Component
public class ValidateAnnouncementMade implements IValidateAnnouncementMade {
    private static final int CONDITION = 2000;
    @Override
    public Boolean validateAnnouncement(String message) {
        return message.length() < CONDITION;
    }
}
