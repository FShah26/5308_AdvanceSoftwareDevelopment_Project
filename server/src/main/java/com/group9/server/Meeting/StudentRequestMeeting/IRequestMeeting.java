package com.group9.server.Meeting.StudentRequestMeeting;

import java.sql.SQLException;

public interface IRequestMeeting {
    void meetingDisplay(String username) ;
    void selectMenu() ;
    void checkinput(String selection) ;
    void manageMeetingAction(String selection);
    void selectCourse(int select);
}
