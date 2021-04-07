package com.group9.server.Announcements.Admin;

import org.springframework.stereotype.Component;

@Component
public class ValidateAnnouncementMade implements IValidateAnnouncementMade {
    final int MAX_ALLOWED_MSG_LENGTH = 2000;

    @Override
    public Boolean validateAnnouncement(String message) {
        return message.length() < MAX_ALLOWED_MSG_LENGTH;
    }
}
