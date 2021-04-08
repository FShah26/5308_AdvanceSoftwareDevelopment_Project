package com.group9.server.Meeting.FacultyManageMeeting;

import com.group9.server.IExecuteAction;

public interface IManageMeeting extends IExecuteAction {

    void meetingDisplay(String username);

    void selectMenu();

    void manageMeetingAction(String selection);

    void takeAction(String selection);

    void checkInput(String selection);

    void displayInvalidMenuOptionMsg();
}

