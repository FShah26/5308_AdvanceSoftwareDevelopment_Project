package com.group9.server.Announcements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementLogic implements IAnnouncementLogic {

    @Autowired
    IValidateAnnouncementMade validate;
    @Autowired
    IAnnouncementPersistence persist;
    @Override
    public String make_announcement(String user_name,String message) {
        String output;
       if(validate.validate_announcement(message)){
           output=persist.InsertAnnouncement(user_name,message);
       }
       else
       {
           output="Please enter only valid message with upto 2000 characters...";
       }
        return output;
    }
}
