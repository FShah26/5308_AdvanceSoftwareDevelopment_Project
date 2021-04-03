package com.group9.server.Meeting.StudentRequestMeeting;

import java.sql.SQLException;

public interface IRequestMeeting {
    void meetingDisplay(String username) throws SQLException;

    void selectMenu() throws SQLException;

    void checkInput(String selection) throws SQLException;

    void manageMeetingAction(String selection);

    void selectCourse(int select) throws SQLException;
}
