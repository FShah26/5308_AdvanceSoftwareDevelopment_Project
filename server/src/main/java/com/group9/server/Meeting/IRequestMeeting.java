package com.group9.server.Meeting;

import java.sql.SQLException;

public interface IRequestMeeting {
    void meetingDisplay(String username) throws SQLException;
    void selectMenu() throws SQLException;
    void checkinput(String selection) throws SQLException;
    void selectCourse(int i)throws SQLException;
}
