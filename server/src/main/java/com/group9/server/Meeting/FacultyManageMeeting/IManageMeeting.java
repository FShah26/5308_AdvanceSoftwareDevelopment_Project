package com.group9.server.Meeting.FacultyManageMeeting;

import java.sql.SQLException;

public interface IManageMeeting {

    void display(String username) ;
    void selectMenu() ;
    void checkInput(String selection);
    void displayInvalidMenuOptionMsg();
}

