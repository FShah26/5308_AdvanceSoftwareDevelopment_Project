package com.group9.server.Announcements.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementLogic implements IAnnouncementLogic {

    @Autowired
    IValidateAnnouncementMade validate;
    @Autowired
    IAnnouncementPersistence persist;
    @Override
    public String make_announcement(String userRole,String message,String userId) {
        String output;
       if(validate.validate_announcement(message)){
           try {
               output = persist.InsertAnnouncement(userRole, message,userId);
           }
           catch(Exception ex){
               output ="Failed to make announcement";
           }
       }
       else
       {
           output="Please enter only valid message with upto 2000 characters...";
       }
        return output;
    }
}
